package com.app.library.util;

import com.app.library.model.dto.BookDto;
import com.app.library.model.entity.BookEntity;

import java.util.List;

import static com.app.library.constant.TestConstants.*;
import static com.app.library.util.BorrowMockFactory.*;

public class BookMockFactory {

    public static BookDto getBookDto() {
        return new BookDto(BOOK_ID_2, BOOK_TITLE_2, BOOK_DESCRIPTION_2, BOOK_YEAR_2, BORROW_DTO_LIST, BUSINESS_VERSION);
    }

    public static BookEntity getBook() {
        BookEntity book = new BookEntity();
        book.setId(BOOK_ID_1);
        book.setTitle(BOOK_TITLE_1);
        book.setDescription(BOOK_DESCRIPTION_1);
        book.setYear(BOOK_YEAR_1);
        book.setBorrows(null);

        return book;
    }

    public static BookEntity getSecondBook() {
        BookEntity book = new BookEntity();
        book.setId(BOOK_ID_2);
        book.setTitle(BOOK_TITLE_2);
        book.setDescription(BOOK_DESCRIPTION_2);
        book.setYear(BOOK_YEAR_2);
        book.setBorrows(List.of(getBorrows()));

        return book;
    }

    public static BookEntity getBorrowedBook() {
        BookEntity book = new BookEntity();
        book.setId(BOOK_ID_1);
        book.setTitle(BOOK_TITLE_1);
        book.setDescription(BOOK_DESCRIPTION_1);
        book.setYear(BOOK_YEAR_1);
        book.setBorrows(List.of(getBorrowed()));

        return book;
    }
}
