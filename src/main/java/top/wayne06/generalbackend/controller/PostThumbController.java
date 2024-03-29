package top.wayne06.generalbackend.controller;

import top.wayne06.generalbackend.common.BaseResponse;
import top.wayne06.generalbackend.common.ErrorCode;
import top.wayne06.generalbackend.common.ResultUtils;
import top.wayne06.generalbackend.exception.BusinessException;
import top.wayne06.generalbackend.model.dto.postthumb.PostThumbAddRequest;
import top.wayne06.generalbackend.model.entity.User;
import top.wayne06.generalbackend.service.PostThumbService;
import top.wayne06.generalbackend.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Post Thumb Controller
 *
 * @author wayne06
 */
@RestController
@RequestMapping("/post_thumb")
@Slf4j
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    @Resource
    private UserService userService;

    /**
     * thumb / un-thumb
     *
     * @param postThumbAddRequest
     * @param request
     * @return resultNum change of thumb number
     */
    @PostMapping("/")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                         HttpServletRequest request) {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }

}
