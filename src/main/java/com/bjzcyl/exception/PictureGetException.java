package com.bjzcyl.exception;

@SuppressWarnings("serial")
public class PictureGetException extends Exception {
    public PictureGetException() {
    }

    public PictureGetException(String message) {
        super(message);
    }

    public PictureGetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PictureGetException(Throwable cause) {
        super(cause);
    }

    public PictureGetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
