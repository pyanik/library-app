package com.app.library.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

    public static final String DB_SCHEMA = "library_app";

    @UtilityClass
    public class CacheNames {
        public static final String BOOKS_CACHE = "booksCache";
        public static final String AUTHORS_CACHE = "authorsCache";
        public static final String AUTHORS_CACHE_CLEARED_MESSAGE = "Authors cache has been cleared.";
        public static final String BOOKS_CACHE_CLEARED_MESSAGE = "Books cache has been cleared.";
    }

    @UtilityClass
    public class ProfileNames {
        public static final String TEST_PROFILE = "unit-test";
    }

    @UtilityClass
    public class ExceptionMessages {
        public static final String IS_ALREADY_BORROWED = "The book is already borrowed.";
        public static final String BOOK_NOT_FOUND = "Book with id %s has not been found.";
        public static final String BORROW_NOT_FOUND = "Borrow with id %s has not been found.";
        public static final String EMAIL_ALREADY_EXISTS = "User with email address %s already exists.";
    }

    @UtilityClass
    public class ControllerMessages {
        public static final String AUTHOR_HAS_BEEN_DELETED = "Author has been deleted.";
        public static final String BOOK_CATEGORY_HAS_BEEN_DELETED = "Book Category has been deleted.";
        public static final String BOOK_HAS_BEEN_DELETED = "Book has been deleted.";
        public static final String BOOK_BORROW_HAS_BEEN_DELETED = "Book Borrow has been deleted.";
        public static final String USER_HAS_BEEN_DELETED = "User has been deleted.";
        public static final String USERS_HAVE_BEEN_DELETED = "Users have been deleted.";
    }
}
