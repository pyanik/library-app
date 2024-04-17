package com.app.library.util;

import com.app.library.model.dto.AuthorDto;
import com.app.library.model.entity.AuthorEntity;

import static com.app.library.constant.TestConstants.*;

public class AuthorMockFactory {

    public static AuthorDto getAuthorDto() {
        return new AuthorDto(AUTHOR_ID, AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME, AUTHOR_BIOGRAPHY, BOOK_DTO_LIST);
    }

    public static AuthorEntity getAuthor() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(AUTHOR_ID);
        authorEntity.setFirstName(AUTHOR_FIRST_NAME);
        authorEntity.setLastName(AUTHOR_LAST_NAME);
        authorEntity.setBiography(AUTHOR_BIOGRAPHY);
        authorEntity.setBooks(BOOK_LIST);

        return authorEntity;
    }

    public static AuthorEntity getAuthorWithNameAsFirstName() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(AUTHOR_ID);
        authorEntity.setFirstName(AUTHOR_NAME);
        authorEntity.setLastName(AUTHOR_LAST_NAME);
        authorEntity.setBiography(AUTHOR_BIOGRAPHY);
        authorEntity.setBooks(BOOK_LIST);

        return authorEntity;
    }

    public static AuthorEntity getAuthorWithNameAsLastName() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(AUTHOR_ID_2);
        authorEntity.setFirstName(AUTHOR_FIRST_NAME);
        authorEntity.setLastName(AUTHOR_NAME);
        authorEntity.setBiography(AUTHOR_BIOGRAPHY);
        authorEntity.setBooks(BOOK_LIST);

        return authorEntity;
    }
}
