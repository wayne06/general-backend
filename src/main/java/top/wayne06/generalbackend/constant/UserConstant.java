package top.wayne06.generalbackend.constant;

/**
 * User constant
 *
 * @author wayne06
 */
public interface UserConstant {

    /**
     * the key for user login state
     */
    String USER_LOGIN_STATE = "user_login";

    //  region authority

    /**
     * default role
     */
    String DEFAULT_ROLE = "user";

    /**
     * admin role
     */
    String ADMIN_ROLE = "admin";

    /**
     * banned role
     */
    String BAN_ROLE = "ban";

    // endregion
}
