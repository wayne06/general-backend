package top.wayne06.generalbackend.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.formula.functions.Odd;
import org.jetbrains.annotations.NotNull;
import top.wayne06.generalbackend.annotation.AuthCheck;
import top.wayne06.generalbackend.common.*;
import top.wayne06.generalbackend.constant.UserConstant;
import top.wayne06.generalbackend.exception.BusinessException;
import top.wayne06.generalbackend.exception.ThrowUtils;
import top.wayne06.generalbackend.model.dto.post.PostAddRequest;
import top.wayne06.generalbackend.model.dto.post.PostEditRequest;
import top.wayne06.generalbackend.model.dto.post.PostQueryRequest;
import top.wayne06.generalbackend.model.dto.post.PostUpdateRequest;
import top.wayne06.generalbackend.model.entity.Post;
import top.wayne06.generalbackend.model.entity.User;
import top.wayne06.generalbackend.model.vo.PostVO;
import top.wayne06.generalbackend.service.PostService;
import top.wayne06.generalbackend.service.UserService;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * post controller
 *
 * @author wayne06
 */
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    // region CRUD

    /**
     * add post
     *
     * @param postAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest, HttpServletRequest request) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);
        List<String> tags = postAddRequest.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        postService.validPost(post, true);
        User loginUser = userService.getLoginUser(request);
        post.setUserId(loginUser.getId());
        post.setFavourNum(0);
        post.setThumbNum(0);
        boolean result = postService.save(post);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPostId = post.getId();
        return ResultUtils.success(newPostId);
    }

    /**
     * delete post
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!currentUserIsCreatorOrAdmin(deleteRequest.getId(), request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = postService.removeById(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /**
     * update post by admin
     *
     * @param postUpdateRequest
     * @return
     */
    @PostMapping("/update-by-admin")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updatePostByAdmin(@RequestBody PostUpdateRequest postUpdateRequest) {
        Post post = getNewPost(postUpdateRequest);
        // check if post exist
        Post oldPost = postService.getById(postUpdateRequest.getId());
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

    /**
     * update post by user
     *
     * @param postUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update-by-user")
    public BaseResponse<Boolean> updatePostByUser(@RequestBody PostUpdateRequest postUpdateRequest,
                                               HttpServletRequest request) {
        Post post = getNewPost(postUpdateRequest);
        if (!currentUserIsCreatorOrAdmin(postUpdateRequest.getId(), request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

    /**
     * get post VO by id
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<PostVO> getPostVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(postService.getPostVO(post, request));
    }

    /**
     * get post list in pagination (admin only)
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Post>> listPostByPage(@RequestBody PostQueryRequest postQueryRequest) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postPage);
    }

    /**
     * get post VO list in pagination
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PostVO>> listPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                       HttpServletRequest request) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        // restrict crawlers
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postService.getPostVOPage(postPage, request));
    }

    /**
     * get my post VO list in pagination
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<PostVO>> listMyPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                         HttpServletRequest request) {
        if (postQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        postQueryRequest.setUserId(loginUser.getId());
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        // restrict crawlers
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postService.getPostVOPage(postPage, request));
    }

    // endregion

    /**
     * search post VO in pagination (from ES)
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/search/page/vo")
    public BaseResponse<Page<PostVO>> searchPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                         HttpServletRequest request) {
        long size = postQueryRequest.getPageSize();
        // restrict crawlers
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return ResultUtils.success(postService.getPostVOPage(postPage, request));
    }


    @NotNull
    private Post getNewPost(PostUpdateRequest postUpdateRequest) {
        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateRequest, post);
        List<String> tags = postUpdateRequest.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        // check parameters
        postService.validPost(post, false);
        return post;
    }

    private boolean currentUserIsCreatorOrAdmin(long postId, HttpServletRequest request) {
        User currentUser = userService.getLoginUser(request);
        // check if post exists
        Post oldPost = postService.getById(postId);
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        // creator or admin only
        if (!oldPost.getUserId().equals(currentUser.getId()) && userService.isAdmin(currentUser)) {
            return false;
        }
        return true;
    }



}
