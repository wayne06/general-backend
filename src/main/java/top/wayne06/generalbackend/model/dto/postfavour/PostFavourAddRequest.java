package top.wayne06.generalbackend.model.dto.postfavour;

import java.io.Serializable;
import lombok.Data;

/**
 * request of favour/ un-favour post
 *
 * @author wayne06
 */
@Data
public class PostFavourAddRequest implements Serializable {

    /**
     * post id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}
