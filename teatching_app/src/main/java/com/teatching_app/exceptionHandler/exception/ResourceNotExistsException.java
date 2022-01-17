package com.teatching_app.exceptionHandler.exception;

public class ResourceNotExistsException extends RuntimeException {

    public ResourceNotExistsException(String message) {
        super(message);
    }
}
