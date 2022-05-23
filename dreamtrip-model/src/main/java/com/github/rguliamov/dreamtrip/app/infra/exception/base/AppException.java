package com.github.rguliamov.dreamtrip.app.infra.exception.base;

/**
 * Base application-specific exception
 *
 * @author Guliamov Rustam
 */
public class AppException extends RuntimeException{

    private static final long serialVersionUID = -4564945315544986720L;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
