package top.wayne06.generalbackend.model.dto.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * request of add post
 *
 * @author wayne06
 */
@Data
public class PostAddRequest implements Serializable {

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
