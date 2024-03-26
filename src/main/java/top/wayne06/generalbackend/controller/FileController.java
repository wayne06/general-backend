package top.wayne06.generalbackend.controller;

import cn.hutool.core.io.FileUtil;
import top.wayne06.generalbackend.common.BaseResponse;
import top.wayne06.generalbackend.common.ErrorCode;
import top.wayne06.generalbackend.common.ResultUtils;
import top.wayne06.generalbackend.constant.FileConstant;
import top.wayne06.generalbackend.exception.BusinessException;
import top.wayne06.generalbackend.manager.CosManager;
import top.wayne06.generalbackend.model.dto.file.UploadFileRequest;
import top.wayne06.generalbackend.model.entity.User;
import top.wayne06.generalbackend.model.enums.FileUploadBizEnum;
import top.wayne06.generalbackend.service.UserService;

import java.io.File;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static top.wayne06.generalbackend.constant.CommonConstant.*;

/**
 * File controller
 *
 * @author wayne06
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private UserService userService;

    @Resource
    private CosManager cosManager;

    /**
     * file upload
     *
     * @param multipartFile
     * @param uploadFileRequest
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile,
                                           UploadFileRequest uploadFileRequest, HttpServletRequest request) {
        String biz = uploadFileRequest.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        validFile(multipartFile, fileUploadBizEnum);
        User loginUser = userService.getLoginUser(request);
        // file path: divide by business and user
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        String filepath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), loginUser.getId(), filename);
        File file = null;
        try {
            // file upload
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // return the access address
            return ResultUtils.success(FileConstant.COS_HOST + filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "Upload failed.");
        } finally {
            if (file != null) {
                // delete temporary file
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

    /**
     * check file
     *
     * @param multipartFile
     * @param fileUploadBizEnum business type
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        long fileSize = multipartFile.getSize();
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "The file size cannot exceed 1M.");
            }
            if (!Arrays.asList(JPEG, JPG, SVG, PNG, WEBP).contains(fileSuffix)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "File type error.");
            }
        }
    }
}
