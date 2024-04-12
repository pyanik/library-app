package com.app.library.exception;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID bookId) {
        super(String.format("Book with id %s has not been found.", bookId));
    }
}
