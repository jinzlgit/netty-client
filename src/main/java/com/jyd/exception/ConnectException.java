package com.jyd.exception;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:40
 */
public class ConnectException extends RuntimeException {

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }
}
