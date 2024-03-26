package top.wayne06.generalbackend.model.dto.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import top.wayne06.generalbackend.common.AbstractRequest;

/**
 * request of update post
 *
 * @author
 */
@Data
public class PostUpdateRequest extends AbstractRequest implements Serializable {

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
     * tag list
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}
