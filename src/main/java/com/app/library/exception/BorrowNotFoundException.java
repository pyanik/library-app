package com.app.library.exception;

import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.ExceptionMessages.BORROW_NOT_FOUND;

public class BorrowNotFoundException extends RuntimeException {
    public BorrowNotFoundException(UUID borrowId) {
        super(String.format(BORROW_NOT_FOUND, borrowId));
    }
}
