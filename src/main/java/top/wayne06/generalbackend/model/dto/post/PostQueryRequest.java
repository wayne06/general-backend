package top.wayne06.generalbackend.model.dto.post;

import top.wayne06.generalbackend.common.PageRequest;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * request of query post
 *
 * @author wayne06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * search text
     */
    private String searchText;

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
     * 1 tag at least
     */
    private List<String> orTags;

    /**
     * user id
     */
    private Long userId;

    /**
     * favour user id
     */
    private Long favourUserId;

    private static final long serialVersionUID = 1L;
}
