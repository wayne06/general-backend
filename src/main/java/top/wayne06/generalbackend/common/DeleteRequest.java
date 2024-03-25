package top.wayne06.generalbackend.common;

import java.io.Serializable;
import lombok.Data;

/**
 * Delete Request
 *
 * @author wayne06
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * record id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
