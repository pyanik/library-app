package com.app.library.exception;

import static com.app.library.constant.ApplicationConstants.ExceptionMessages.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(String.format(EMAIL_ALREADY_EXISTS, email));
    }
}
