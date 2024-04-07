package com.app.library.exception;

import java.util.UUID;

public class BorrowNotFoundException extends RuntimeException {
    public BorrowNotFoundException(UUID borrowId) {
        super(String.format("Borrow with id %s has not been found.", borrowId));
    }
}
