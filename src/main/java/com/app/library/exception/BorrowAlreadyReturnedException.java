package com.app.library.exception;

import java.util.UUID;

public class BorrowAlreadyReturnedException extends RuntimeException{
    public BorrowAlreadyReturnedException(UUID borrowId) {
        super(String.format("Book for Borrow with id %s has been already returned.", borrowId));
    }
}
