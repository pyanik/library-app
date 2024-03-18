package com.app.library;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.model.entity.BookCategoryEntity;

import static com.app.library.TestConstants.*;

class BookCategoryMockFactory {

    public static BookCategoryDto getBookCategoryDto() {
        return new BookCategoryDto(BOOK_CATEGORY_ID, BOOK_CATEGORY_NAME, BOOK_CATEGORY_DESCRIPTION, BOOK_DTO_LIST);
    }

    public static BookCategoryEntity getBookCategory() {
        BookCategoryEntity bookCategory = new BookCategoryEntity();
        bookCategory.setId(BOOK_CATEGORY_ID);
        bookCategory.setName(BOOK_CATEGORY_NAME);
        bookCategory.setDescription(BOOK_CATEGORY_DESCRIPTION);
        bookCategory.setBooks(BOOK_LIST);

        return bookCategory;
    }
}
