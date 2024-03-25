package top.wayne06.generalbackend.exception;

import top.wayne06.generalbackend.common.ErrorCode;

/**
 * Throw exception utils
 *
 * @author https://github.com/wayne06
 */
public class ThrowUtils {

    /**
     * throw exception if condition is met
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * throw exception if condition is met
     *
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * throw exception if condition is met
     *
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
