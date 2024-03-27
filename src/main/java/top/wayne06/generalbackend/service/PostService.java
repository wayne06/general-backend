package top.wayne06.generalbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wayne06.generalbackend.model.dto.post.PostQueryRequest;
import top.wayne06.generalbackend.model.entity.Post;
import top.wayne06.generalbackend.model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

/**
 * post service
 *
 * @author wayne06
 */
public interface PostService extends IService<Post> {

    /**
     * check post validity
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * get query wrapper
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * search from es
     *
     * @param postQueryRequest
     * @return
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);

    /**
     * get post vo
     *
     * @param post
     * @param request
     * @return
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * get post vo in pagination
     *
     * @param postPage
     * @param request
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);
}
