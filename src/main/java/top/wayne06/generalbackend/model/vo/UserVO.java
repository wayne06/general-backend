package top.wayne06.generalbackend.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * User view object (desensitization)
 *
 * @author wayne06
 */
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String userName;

    /**
     * user avatar
     */
    private String userAvatar;

    /**
     * user profile
     */
    private String userProfile;

    /**
     * user role：user/admin/ban
     */
    private String userRole;

    /**
     * create time
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
