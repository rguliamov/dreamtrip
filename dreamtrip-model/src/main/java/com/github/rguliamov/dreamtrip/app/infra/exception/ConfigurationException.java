package com.github.rguliamov.dreamtrip.app.infra.exception;

import com.github.rguliamov.dreamtrip.app.infra.exception.base.AppException;

/**
 * Signals about incorrect configuration settings/parameters
 *
 * @author Guliamov Rustam
 */
public class ConfigurationException extends  AppException {

    private static final long serialVersionUID = -3203886136035717074L;

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }
}
