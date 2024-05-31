package com.app.library.exception;

import static com.app.library.constant.ApplicationConstants.ExceptionMessages.IS_ALREADY_BORROWED;

public class BookAlreadyBorrowedException extends Exception {

    public BookAlreadyBorrowedException() {
        super(IS_ALREADY_BORROWED);
    }
}
