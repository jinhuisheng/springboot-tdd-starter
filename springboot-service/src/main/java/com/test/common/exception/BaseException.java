package com.test.common.exception;

/**
 * @author huisheng.jin
 * @version 2019/4/14.
 */
public class BaseException extends RuntimeException {
    private String tipMessage;

    public BaseException(String tipMessage) {
        super(tipMessage);
        this.tipMessage = tipMessage;
    }

    public BaseException(String message, String tipMessage) {
        super(message);
        this.tipMessage = tipMessage;
    }

    public BaseException(String message, Throwable cause, String tipMessage) {
        super(message, cause);
        this.tipMessage = tipMessage;
    }

    public String getTipMessage() {
        return this.tipMessage;
    }

    public BaseException() {
    }
}
