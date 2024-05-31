package com.app.library.exception;

import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.ExceptionMessages.BOOK_NOT_FOUND;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(UUID bookId) {
        super(String.format(BOOK_NOT_FOUND, bookId));
    }
}
