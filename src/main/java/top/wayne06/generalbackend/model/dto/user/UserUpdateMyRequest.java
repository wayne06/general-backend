package top.wayne06.generalbackend.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * request of user update his own info
 *
 * @author wayne06
 */
@Data
public class UserUpdateMyRequest implements Serializable {

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

    private static final long serialVersionUID = 1L;
}
