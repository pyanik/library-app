package com.app.library;

import com.app.library.model.dto.AuthorDto;
import com.app.library.model.dto.BookCategoryDto;
import com.app.library.model.dto.UserDto;
import com.app.library.model.entity.AuthorEntity;
import com.app.library.model.entity.BookCategoryEntity;
import com.app.library.model.entity.BookEntity;
import com.app.library.model.entity.UserEntity;
import com.app.library.model.mapper.AuthorMapper;
import com.app.library.model.mapper.BookCategoryMapper;
import com.app.library.model.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapAuthorDtoToEntityTest() {
        //given
        AuthorDto authorDto = AuthorMockFactory.getAuthorDto();

        //when
        AuthorEntity author = authorMapper.toEntity(authorDto);

        //then
        Assertions.assertNotNull(author);
        Assertions.assertEquals(authorDto.id(), author.getId());
        Assertions.assertEquals(authorDto.name(), author.getFirstName());
        Assertions.assertEquals(authorDto.surname(), author.getLastName());
        Assertions.assertEquals(authorDto.biography(), author.getBiography());
        Assertions.assertEquals(authorDto.books().get(0).title(), author.getBooks().get(0).getTitle());
        Assertions.assertEquals(authorDto.books().get(0).description(), author.getBooks().get(0).getDescription());
        Assertions.assertEquals(authorDto.books().get(0).year(), author.getBooks().get(0).getYear());
        Assertions.assertNull(authorDto.books().get(0).borrows());
        Assertions.assertEquals(authorDto.books().get(1).title(), author.getBooks().get(1).getTitle());
        Assertions.assertEquals(authorDto.books().get(1).description(), author.getBooks().get(1).getDescription());
        Assertions.assertEquals(authorDto.books().get(1).year(), author.getBooks().get(1).getYear());
        Assertions.assertEquals(authorDto.books().get(1).borrows().get(0).dateOfBorrow(), author.getBooks().get(1).getBorrows().get(0).getDateOfBorrow());
        Assertions.assertEquals(authorDto.books().get(1).borrows().get(0).dateOfReturn(), author.getBooks().get(1).getBorrows().get(0).getDateOfReturn());
        Assertions.assertEquals(authorDto.books().get(1).borrows().get(0).borrowStatus(), author.getBooks().get(1).getBorrows().get(0).getBorrowStatus());
        Assertions.assertEquals(authorDto.books().get(1).borrows().get(1).dateOfBorrow(), author.getBooks().get(1).getBorrows().get(1).getDateOfBorrow());
        Assertions.assertNull(authorDto.books().get(1).borrows().get(1).dateOfReturn());
        Assertions.assertEquals(authorDto.books().get(1).borrows().get(1).borrowStatus(), author.getBooks().get(1).getBorrows().get(1).getBorrowStatus());
    }

    @Test
    public void mapAuthorEntityToDtoTest() {
        //given
        AuthorEntity author = AuthorMockFactory.getAuthor();

        //when
        AuthorDto authorDto = authorMapper.toDto(author);

        //then
        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(author.getId(), authorDto.id());
        Assertions.assertEquals(author.getFirstName(), authorDto.name());
        Assertions.assertEquals(author.getLastName(), authorDto.surname());
        Assertions.assertEquals(author.getBiography(), authorDto.biography());
        Assertions.assertEquals(author.getBooks().get(0).getTitle(), authorDto.books().get(0).title());
        Assertions.assertEquals(author.getBooks().get(0).getDescription(), authorDto.books().get(0).description());
        Assertions.assertEquals(author.getBooks().get(0).getYear(), authorDto.books().get(0).year());
        Assertions.assertTrue(author.getBooks().get(0).getBorrows().isEmpty());
        Assertions.assertEquals(author.getBooks().get(1).getTitle(), authorDto.books().get(1).title());
        Assertions.assertEquals(author.getBooks().get(1).getDescription(), authorDto.books().get(1).description());
        Assertions.assertEquals(author.getBooks().get(1).getYear(), authorDto.books().get(1).year());
        Assertions.assertEquals(author.getBooks().get(1).getBorrows().get(0).getDateOfBorrow(), authorDto.books().get(1).borrows().get(0).dateOfBorrow());
        Assertions.assertEquals(author.getBooks().get(1).getBorrows().get(0).getDateOfReturn(), authorDto.books().get(1).borrows().get(0).dateOfReturn());
        Assertions.assertEquals(author.getBooks().get(1).getBorrows().get(0).getBorrowStatus(), authorDto.books().get(1).borrows().get(0).borrowStatus());
        Assertions.assertEquals(author.getBooks().get(1).getBorrows().get(1).getDateOfBorrow(), authorDto.books().get(1).borrows().get(1).dateOfBorrow());
        Assertions.assertNull(author.getBooks().get(1).getBorrows().get(1).getDateOfReturn());
        Assertions.assertEquals(author.getBooks().get(1).getBorrows().get(1).getBorrowStatus(), authorDto.books().get(1).borrows().get(1).borrowStatus());
    }

    @Test
    public void mapBookCategoryDtoToEntityTest() {
        //given
        BookCategoryDto bookCategoryDto = BookCategoryMockFactory.getBookCategoryDto();

        //when
        BookCategoryEntity bookCategory = bookCategoryMapper.toEntity(bookCategoryDto);

        //then
        Assertions.assertNotNull(bookCategory);
        Assertions.assertEquals(bookCategoryDto.id(), bookCategory.getId());
        Assertions.assertEquals(bookCategoryDto.name(), bookCategory.getName());
        Assertions.assertEquals(bookCategoryDto.description(), bookCategory.getDescription());
        String title = bookCategoryDto.books().get(0).title();
        List<BookEntity> title1 = bookCategory.getBooks();
        BookEntity bookEntity = title1.get(0);
        String title2 = bookEntity.getTitle();
        Assertions.assertEquals(bookCategoryDto.books().get(0).title(), bookCategory.getBooks().get(0).getTitle());
        Assertions.assertEquals(bookCategoryDto.books().get(0).description(), bookCategory.getBooks().get(0).getDescription());
        Assertions.assertEquals(bookCategoryDto.books().get(0).year(), bookCategory.getBooks().get(0).getYear());
        Assertions.assertNull(bookCategoryDto.books().get(0).borrows());
        Assertions.assertEquals(bookCategoryDto.books().get(1).title(), bookCategory.getBooks().get(1).getTitle());
        Assertions.assertEquals(bookCategoryDto.books().get(1).description(), bookCategory.getBooks().get(1).getDescription());
        Assertions.assertEquals(bookCategoryDto.books().get(1).year(), bookCategory.getBooks().get(1).getYear());
        Assertions.assertEquals(bookCategoryDto.books().get(1).borrows().get(0).dateOfBorrow(), bookCategory.getBooks().get(1).getBorrows().get(0).getDateOfBorrow());
        Assertions.assertEquals(bookCategoryDto.books().get(1).borrows().get(0).dateOfReturn(), bookCategory.getBooks().get(1).getBorrows().get(0).getDateOfReturn());
        Assertions.assertEquals(bookCategoryDto.books().get(1).borrows().get(0).borrowStatus(), bookCategory.getBooks().get(1).getBorrows().get(0).getBorrowStatus());
        Assertions.assertEquals(bookCategoryDto.books().get(1).borrows().get(1).dateOfBorrow(), bookCategory.getBooks().get(1).getBorrows().get(1).getDateOfBorrow());
        Assertions.assertNull(bookCategoryDto.books().get(1).borrows().get(1).dateOfReturn());
        Assertions.assertEquals(bookCategoryDto.books().get(1).borrows().get(1).borrowStatus(), bookCategory.getBooks().get(1).getBorrows().get(1).getBorrowStatus());
    }

    @Test
    public void mapBookCategoryEntityToDtoTest() {
        //given
        BookCategoryEntity bookCategory = BookCategoryMockFactory.getBookCategory();

        //when
        BookCategoryDto bookCategoryDto = bookCategoryMapper.toDto(bookCategory);

        //then
        Assertions.assertNotNull(bookCategory);
        Assertions.assertEquals(bookCategory.getId(), bookCategoryDto.id());
        Assertions.assertEquals(bookCategory.getName(), bookCategoryDto.name());
        Assertions.assertEquals(bookCategory.getDescription(), bookCategoryDto.description());
        Assertions.assertEquals(bookCategory.getBooks().get(0).getTitle(), bookCategoryDto.books().get(0).title());
        Assertions.assertEquals(bookCategory.getBooks().get(0).getDescription(), bookCategoryDto.books().get(0).description());
        Assertions.assertEquals(bookCategory.getBooks().get(0).getYear(), bookCategoryDto.books().get(0).year());
        Assertions.assertTrue(bookCategory.getBooks().get(0).getBorrows().isEmpty());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getTitle(), bookCategoryDto.books().get(1).title());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getDescription(), bookCategoryDto.books().get(1).description());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getYear(), bookCategoryDto.books().get(1).year());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getBorrows().get(0).getDateOfBorrow(), bookCategoryDto.books().get(1).borrows().get(0).dateOfBorrow());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getBorrows().get(0).getDateOfReturn(), bookCategoryDto.books().get(1).borrows().get(0).dateOfReturn());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getBorrows().get(0).getBorrowStatus(), bookCategoryDto.books().get(1).borrows().get(0).borrowStatus());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getBorrows().get(1).getDateOfBorrow(), bookCategoryDto.books().get(1).borrows().get(1).dateOfBorrow());
        Assertions.assertNull(bookCategory.getBooks().get(1).getBorrows().get(1).getDateOfReturn());
        Assertions.assertEquals(bookCategory.getBooks().get(1).getBorrows().get(1).getBorrowStatus(), bookCategoryDto.books().get(1).borrows().get(1).borrowStatus());
    }

    @Test
    public void mapUserDtoToEntityTest() {
        //given
        UserDto readerUserDto = UserMockFactory.getReaderUserDto();

        //when
        UserEntity readerUser = userMapper.toEntity(readerUserDto);

        //then
        Assertions.assertNotNull(readerUser);
        Assertions.assertEquals(readerUserDto.id(), readerUser.getId());
        Assertions.assertEquals(readerUserDto.name(), readerUser.getFirstName());
        Assertions.assertEquals(readerUserDto.surname(), readerUser.getLastName());
        Assertions.assertEquals(readerUserDto.email(), readerUser.getEmail());
        Assertions.assertEquals(readerUserDto.password(), readerUser.getPassword());
        Assertions.assertEquals(readerUserDto.userRole(), readerUser.getUserRole());
        Assertions.assertEquals(readerUserDto.borrows().get(0).dateOfBorrow(), readerUser.getBorrows().get(0).getDateOfBorrow());
        Assertions.assertEquals(readerUserDto.borrows().get(0).dateOfReturn(), readerUser.getBorrows().get(0).getDateOfReturn());
        Assertions.assertEquals(readerUserDto.borrows().get(0).borrowStatus(), readerUser.getBorrows().get(0).getBorrowStatus());
        Assertions.assertEquals(readerUserDto.borrows().get(1).dateOfBorrow(), readerUser.getBorrows().get(1).getDateOfBorrow());
        Assertions.assertNull(readerUserDto.borrows().get(1).dateOfReturn());
        Assertions.assertEquals(readerUserDto.borrows().get(1).borrowStatus(), readerUser.getBorrows().get(1).getBorrowStatus());
    }

    @Test
    public void mapUserEntityToDtoTest() {
        //given
        UserEntity readerUser = UserMockFactory.getReaderUser();

        //when
        UserDto readerUserDto = userMapper.toDto(readerUser);

        //then
        Assertions.assertNotNull(readerUserDto);
        Assertions.assertEquals(readerUser.getId(), readerUserDto.id());
        Assertions.assertEquals(readerUser.getFirstName(), readerUserDto.name());
        Assertions.assertEquals(readerUser.getLastName(), readerUserDto.surname());
        Assertions.assertEquals(readerUser.getEmail(), readerUserDto.email());
        Assertions.assertEquals(readerUser.getPassword(), readerUserDto.password());
        Assertions.assertEquals(readerUser.getUserRole(), readerUserDto.userRole());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getDateOfBorrow(), readerUserDto.borrows().get(0).dateOfBorrow());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getDateOfReturn(), readerUserDto.borrows().get(0).dateOfReturn());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getBorrowStatus(), readerUserDto.borrows().get(0).borrowStatus());
        Assertions.assertEquals(readerUser.getBorrows().get(1).getDateOfBorrow(), readerUserDto.borrows().get(1).dateOfBorrow());
        Assertions.assertNull(readerUser.getBorrows().get(1).getDateOfReturn());
        Assertions.assertEquals(readerUser.getBorrows().get(1).getBorrowStatus(), readerUserDto.borrows().get(1).borrowStatus());
    }
}
