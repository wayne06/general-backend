package top.wayne06.generalbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.wayne06.generalbackend.model.entity.Post;
import top.wayne06.generalbackend.model.entity.PostFavour;
import org.apache.ibatis.annotations.Param;

/**
 * operation in database for post favour
 *
 * @author wayne06
 */
public interface PostFavourMapper extends BaseMapper<PostFavour> {

    /**
     * get favour posts in pagination
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, @Param(Constants.WRAPPER) Wrapper<Post> queryWrapper,
            long favourUserId);

}




