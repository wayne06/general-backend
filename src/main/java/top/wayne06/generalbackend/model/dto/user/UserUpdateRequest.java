package top.wayne06.generalbackend.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * request of user update
 *
 * @author wayne06
 */
@Data
public class UserUpdateRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
