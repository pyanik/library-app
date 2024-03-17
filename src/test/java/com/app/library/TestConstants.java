package com.app.library;

import com.app.library.model.dto.BookDto;
import com.app.library.model.dto.BorrowDto;
import com.app.library.model.entity.BookEntity;
import com.app.library.model.entity.BorrowEntity;
import com.app.library.model.enums.BorrowStatus;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestConstants {

    public static final UUID ID_1 = UUID.fromString("0d2a680e-b530-40cc-8b62-fb4517289122");
    public static final UUID ID_2 = UUID.fromString("c8c529a0-2d72-468a-96a3-a71c2d4e0f7d");
    public static final UUID ID_3 = UUID.fromString("11a823f8-561d-4760-92aa-6d4cacd0e4ae");
    public static final UUID ID_4 = UUID.fromString("bc540af1-1f11-48d3-87ec-f906618e3624");
    public static final UUID ID_5 = UUID.fromString("dc3995d5-4d59-4ea5-bcd3-8cc9e27322b2");
    public static final UUID ID_6 = UUID.fromString("60b7c9f4-2a57-40ef-abae-d60ebc745a3b");
    public static final UUID ID_7 = UUID.fromString("cbabe4d6-8e33-4dad-9183-431cae20bd8f");
    public static final String AUTHOR_FIRST_NAME = "Author First Name";
    public static final String AUTHOR_LAST_NAME = "Author Last Name";
    public static final String AUTHOR_BIOGRAPHY = "Author Biography";
    public static final LocalDateTime TEN_DAYS_AGO = Instant.now().minus(Duration.ofDays(10)).atZone(ZoneId.systemDefault()).toLocalDateTime();
    public static final LocalDateTime FIVE_DAYS_AGO = Instant.now().minus(Duration.ofDays(5)).atZone(ZoneId.systemDefault()).toLocalDateTime();
    public static final String BOOK_TITLE_1 = "Book Title 1";
    public static final String BOOK_DESCRIPTION_1 = "Book Description 1";
    public static final Year BOOK_YEAR_1 = Year.of(2016);
    public static final String BOOK_TITLE_2 = "Book Title 2";
    public static final String BOOK_DESCRIPTION_2 = "Book Description 2";
    public static final Year BOOK_YEAR_2 = Year.of(2022);
    public static final String BOOK_CATEGORY_NAME = "Book Category Name";
    public static final String BOOK_CATEGORY_DESCRIPTION = "Book Category Description";
    public static final String USER_FIRST_NAME = "User First Name";
    public static final String USER_LAST_NAME = "User Last Name";
    public static final String USER_EMAIL = "User Email";
    public static final String USER_PASSWORD = "User Password";
    public static final List<BorrowDto> BORROW_DTO_LIST = new ArrayList<>() {{
        add(new BorrowDto(ID_2, TEN_DAYS_AGO, FIVE_DAYS_AGO, BorrowStatus.RETURNED));
        add(new BorrowDto(ID_3, FIVE_DAYS_AGO, null, BorrowStatus.BORROWED));
    }};
    public static final List<BookDto> BOOK_DTO_LIST = new ArrayList<>() {{
        add(new BookDto(ID_4, BOOK_TITLE_1, BOOK_DESCRIPTION_1, BOOK_YEAR_1, null));
        add(new BookDto(ID_5, BOOK_TITLE_2, BOOK_DESCRIPTION_2, BOOK_YEAR_2, BORROW_DTO_LIST));
    }};
    public static final List<BorrowEntity> BORROW_LIST = new ArrayList<>() {{
        BorrowEntity borrowEntity1 = new BorrowEntity();
        borrowEntity1.setId(ID_2);
        borrowEntity1.setDateOfBorrow(TEN_DAYS_AGO);
        borrowEntity1.setDateOfReturn(FIVE_DAYS_AGO);
        borrowEntity1.setBorrowStatus(BorrowStatus.RETURNED);

        BorrowEntity borrowEntity2 = new BorrowEntity();
        borrowEntity2.setId(ID_3);
        borrowEntity2.setDateOfBorrow(FIVE_DAYS_AGO);
        borrowEntity2.setBorrowStatus(BorrowStatus.BORROWED);

        add(borrowEntity1);
        add(borrowEntity2);
    }};
    public static final List<BookEntity> BOOK_LIST = new ArrayList<>() {{
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setId(ID_4);
        bookEntity1.setTitle(BOOK_TITLE_1);
        bookEntity1.setDescription(BOOK_DESCRIPTION_1);
        bookEntity1.setYear(BOOK_YEAR_1);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setId(ID_5);
        bookEntity2.setTitle(BOOK_TITLE_2);
        bookEntity2.setDescription(BOOK_DESCRIPTION_2);
        bookEntity2.setYear(BOOK_YEAR_2);
        bookEntity2.setBorrows(BORROW_LIST);

        add(bookEntity1);
        add(bookEntity2);
    }};
}
