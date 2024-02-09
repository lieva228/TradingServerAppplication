package com.example.backend.bybit.constant.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * An exception which can occur while invoking methods of the Bybit API.
 */
@Getter
public class BybitApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3788669840036201041L;
    private BybitApiError error;

    public BybitApiException(BybitApiError error) {
        this.error = error;
    }

    public BybitApiException() {
        super();
    }

    public BybitApiException(String message) {
        super(message);
    }

    public BybitApiException(Throwable cause) {
        super(cause);
    }

    public BybitApiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}