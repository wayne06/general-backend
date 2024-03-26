package top.wayne06.generalbackend.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * logged in user view object (desensitization)
 *
 * @author wayne06
 **/
@Data
public class LoginUserVO implements Serializable {

    /**
     * user id
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

    /**
     * update time
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
