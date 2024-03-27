package top.wayne06.generalbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wayne06.generalbackend.model.entity.Post;
import top.wayne06.generalbackend.model.entity.PostFavour;
import top.wayne06.generalbackend.model.entity.User;

/**
 * Post favour service
 *
 * @author wayne06
 */
public interface PostFavourService extends IService<PostFavour> {

    /**
     * post favour
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostFavour(long postId, User loginUser);

    /**
     * get favour posts in pagination
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, Wrapper<Post> queryWrapper, long favourUserId);

    /**
     * post favour (inner service)
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostFavourInner(long userId, long postId);
}
