package top.wayne06.generalbackend.model.vo;

import cn.hutool.json.JSONUtil;
import top.wayne06.generalbackend.model.entity.Post;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Post view object (desensitization)
 *
 * @author wayne06
 */
@Data
public class PostVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * title
     */
    private String title;

    /**
     * content
     */
    private String content;

    /**
     * thumb number
     */
    private Integer thumbNum;

    /**
     * favour number
     */
    private Integer favourNum;

    /**
     * user id
     */
    private Long userId;

    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    /**
     * tag list
     */
    private List<String> tagList;

    /**
     * creater
     */
    private UserVO user;

    /**
     * has thumb
     */
    private Boolean hasThumb;

    /**
     * has favour
     */
    private Boolean hasFavour;

    /**
     * postVO to object
     *
     * @param postVO
     * @return
     */
    public static Post voToObj(PostVO postVO) {
        if (postVO == null) {
            return null;
        }
        Post post = new Post();
        BeanUtils.copyProperties(postVO, post);
        List<String> tagList = postVO.getTagList();
        post.setTags(JSONUtil.toJsonStr(tagList));
        return post;
    }

    /**
     * object to postVO
     *
     * @param post
     * @return
     */
    public static PostVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        postVO.setTagList(JSONUtil.toList(post.getTags(), String.class));
        return postVO;
    }
}
