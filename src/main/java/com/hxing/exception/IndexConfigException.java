package com.hxing.exception;

/**
 * Created by wanghongxing on 15/10/19.
 */
public class IndexConfigException extends IndexException {

    public IndexConfigException() {
        super();
    }

    public IndexConfigException(String message) {
        super(message);
    }

    public IndexConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexConfigException(Throwable cause) {
        super(cause);
    }

    protected IndexConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
