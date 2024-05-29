package com.app.library.exception;

import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException {

    public static final String OBJECT_NOT_FOUND = "Database object with id %s has not been found.";

    public ObjectNotFoundException(UUID bookId) {
        super(String.format(OBJECT_NOT_FOUND, bookId));
    }
}
