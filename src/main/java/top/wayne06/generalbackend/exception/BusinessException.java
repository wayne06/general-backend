package top.wayne06.generalbackend.exception;

import top.wayne06.generalbackend.common.ErrorCode;

/**
 * Customized exception
 *
 * @author https://github.com/wayne06
 */
public class BusinessException extends RuntimeException {

    /**
     * error code
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
