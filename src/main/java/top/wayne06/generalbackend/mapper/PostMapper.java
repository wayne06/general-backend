package top.wayne06.generalbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.wayne06.generalbackend.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
 * operation in database for post handling
 *
 * @author wayne06
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * get post list (include deleted)
     *
     * @param minUpdateTime
     * @return
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




