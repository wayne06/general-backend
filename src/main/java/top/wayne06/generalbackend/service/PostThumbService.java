package top.wayne06.generalbackend.service;

import top.wayne06.generalbackend.model.entity.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import top.wayne06.generalbackend.model.entity.User;

/**
 * post thumb service
 *
 * @author wayne06
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * post thumb
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * post thumb (inner service)
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}
