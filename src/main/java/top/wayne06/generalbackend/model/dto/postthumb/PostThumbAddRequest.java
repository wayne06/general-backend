package top.wayne06.generalbackend.model.dto.postthumb;

import java.io.Serializable;
import lombok.Data;

/**
 * request of thumb post
 *
 * @author wayne06
 */
@Data
public class PostThumbAddRequest implements Serializable {

    /**
     * post id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}
