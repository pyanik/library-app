package com.app.library.exception;

public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException() {
        super("The book is already borrowed.");
    }
}
