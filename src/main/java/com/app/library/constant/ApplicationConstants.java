package com.app.library.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

    public static final String DB_SCHEMA = "library_app";

    @UtilityClass
    public class CacheNames {
        public static final String BOOKS_CACHE = "booksCache";
        public static final String AUTHORS_CACHE = "authorsCache";
    }
}
