package com.apptime.auth.exception;

public class UserExistingException extends Exception {
    public UserExistingException() {
    }

    public UserExistingException(String message) {
        super(message);
    }

    public UserExistingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistingException(Throwable cause) {
        super(cause);
    }

    public UserExistingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
