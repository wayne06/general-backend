package top.wayne06.generalbackend.common;

/**
 * Customized error code
 *
 * @author wayne
 */
public enum ErrorCode {

    /**
     * success
     */
    SUCCESS(0, "ok"),

    /**
     * parameters error
     */
    PARAMS_ERROR(40000, "parameters error"),

    /**
     * not login error
     */
    NOT_LOGIN_ERROR(40100, "not login error"),

    /**
     * no authority error
     */
    NO_AUTH_ERROR(40101, "no authority error"),

    /**
     * not found error
     */
    NOT_FOUND_ERROR(40400, "not found error"),

    /**
     * forbidden error
     */
    FORBIDDEN_ERROR(40300, "forbidden error"),

    /**
     * system error
     */
    SYSTEM_ERROR(50000, "system error"),

    /**
     * operation error
     */
    OPERATION_ERROR(50001, "operation error");

    /**
     * code
     */
    private final int code;

    /**
     * message
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
