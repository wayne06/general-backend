package top.wayne06.generalbackend.model.dto.file;

import java.io.Serializable;
import lombok.Data;

/**
 * file upload request
 *
 * @author wayne06
 */
@Data
public class UploadFileRequest implements Serializable {

    /**
     * business
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}
