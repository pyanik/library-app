package com.app.library.exception;

public class BusinessObjectVersionNotValidException extends RuntimeException {
    public BusinessObjectVersionNotValidException(String message) {
        super(message);
    }
}