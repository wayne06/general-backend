package top.wayne06.generalbackend.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * request of add user
 *
 * @author wayne06
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * username
     */
    private String userName;

    /**
     * user account
     */
    private String userAccount;

    /**
     * user avatar
     */
    private String userAvatar;

    /**
     * user role: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}
