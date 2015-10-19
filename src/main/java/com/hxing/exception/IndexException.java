package com.hxing.exception;

/**
 * Created by wanghongxing on 15/10/19.
 */
public class IndexException extends RuntimeException {

    public IndexException() {
        super();
    }

    public IndexException(String message) {
        super(message);
    }

    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexException(Throwable cause) {
        super(cause);
    }

    protected IndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
