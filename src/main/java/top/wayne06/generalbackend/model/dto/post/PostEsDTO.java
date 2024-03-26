package top.wayne06.generalbackend.model.dto.post;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import top.wayne06.generalbackend.model.entity.Post;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Post es dto
 * TODO add @Document(indexName = "post") to the class to enable ES (need to configure ES in advance)
 *
 * @author wayne06
 **/
@Data
public class PostEsDTO implements Serializable {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * id
     */
    @Id
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
     * tag list
     */
    private List<String> tags;

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
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * update time
     */
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * is deleted
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * object to dto
     *
     * @param post
     * @return
     */
    public static PostEsDTO objToDto(Post post) {
        if (post == null) {
            return null;
        }
        PostEsDTO postEsDTO = new PostEsDTO();
        BeanUtils.copyProperties(post, postEsDTO);
        String tagsStr = post.getTags();
        if (StringUtils.isNotBlank(tagsStr)) {
            postEsDTO.setTags(JSONUtil.toList(tagsStr, String.class));
        }
        return postEsDTO;
    }

    /**
     * dto to object
     *
     * @param postEsDTO
     * @return
     */
    public static Post dtoToObj(PostEsDTO postEsDTO) {
        if (postEsDTO == null) {
            return null;
        }
        Post post = new Post();
        BeanUtils.copyProperties(postEsDTO, post);
        List<String> tagList = postEsDTO.getTags();
        if (CollUtil.isNotEmpty(tagList)) {
            post.setTags(JSONUtil.toJsonStr(tagList));
        }
        return post;
    }
}
