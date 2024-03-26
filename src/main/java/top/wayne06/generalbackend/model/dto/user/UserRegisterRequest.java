package top.wayne06.generalbackend.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * request of user register
 *
 * @author wayne06
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
