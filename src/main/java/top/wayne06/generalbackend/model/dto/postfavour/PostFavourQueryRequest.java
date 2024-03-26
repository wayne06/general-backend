package top.wayne06.generalbackend.model.dto.postfavour;

import top.wayne06.generalbackend.common.PageRequest;
import top.wayne06.generalbackend.model.dto.post.PostQueryRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * request of query post favour data
 *
 * @author wayne06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * request of query post
     */
    private PostQueryRequest postQueryRequest;

    /**
     * user id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
