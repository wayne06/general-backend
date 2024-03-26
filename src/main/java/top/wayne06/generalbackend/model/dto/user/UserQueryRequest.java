package top.wayne06.generalbackend.model.dto.user;

import top.wayne06.generalbackend.common.PageRequest;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * request of query user
 *
 * @author wayne06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * open platform id
     */
    private String unionId;

    /**
     * official account openId
     */
    private String mpOpenId;

    /**
     * username
     */
    private String userName;

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
