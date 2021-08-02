package com.jyd.exception;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:50
 */
public class DisconnectException extends RuntimeException {
    public DisconnectException(String message) {
        super(message);
    }

    public DisconnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisconnectException(Throwable cause) {
        super(cause);
    }
}
