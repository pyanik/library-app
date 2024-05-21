package com.app.library.util;

import com.app.library.model.dto.BorrowDto;
import com.app.library.model.entity.BorrowEntity;
import com.app.library.model.enums.BorrowStatus;

import java.util.List;

import static com.app.library.constant.TestConstants.*;
import static com.app.library.util.BookMockFactory.getBook;
import static com.app.library.util.BookMockFactory.getSecondBook;
import static com.app.library.util.UserMockFactory.getReaderUser;

public class BorrowMockFactory {

    public static BorrowDto getBorrowDto() {
        return new BorrowDto(BORROW_ID_1, BOOK_ID_1, USER_ID, DATE_OF_BORROW_1, DATE_OF_RETURN_1, BorrowStatus.RETURNED, BUSINESS_VERSION);
    }

    public static BorrowEntity getBorrow() {
        BorrowEntity borrow = new BorrowEntity();
        borrow.setId(BORROW_ID_1);
        borrow.setBook(getBook());
        borrow.setUser(getReaderUser());
        borrow.setDateOfBorrow(DATE_OF_BORROW_1);
        borrow.setDateOfReturn(DATE_OF_RETURN_1);
        borrow.setBorrowStatus(BorrowStatus.RETURNED);

        return borrow;
    }

    public static BorrowEntity getBorrows() {
        BorrowEntity borrow = new BorrowEntity();
        borrow.setId(BORROW_ID_1);
        borrow.setBook(getBook());
        borrow.setUser(getReaderUser());
        borrow.setDateOfBorrow(DATE_OF_BORROW_1);
        borrow.setDateOfReturn(DATE_OF_RETURN_1);
        borrow.setBorrowStatus(BorrowStatus.RETURNED);

        return borrow;
    }

    public static BorrowEntity getBorrowed() {
        BorrowEntity borrow = new BorrowEntity();
        borrow.setId(BORROW_ID_1);
        borrow.setBook(getBook());
        borrow.setUser(getReaderUser());
        borrow.setDateOfBorrow(DATE_OF_BORROW_1);
        borrow.setDateOfReturn(null);
        borrow.setBorrowStatus(BorrowStatus.BORROWED);

        return borrow;
    }
}
