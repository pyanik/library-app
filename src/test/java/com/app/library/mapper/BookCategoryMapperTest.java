package com.app.library.mapper;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.model.entity.BookCategoryEntity;
import com.app.library.model.entity.BookEntity;
import com.app.library.model.mapper.BookCategoryMapper;
import com.app.library.model.mapper.BookMapper;
import com.app.library.model.mapper.BorrowMapper;
import com.app.library.util.BookCategoryMockFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookCategoryMapperTest {

    private BookCategoryMapper bookCategoryMapper;

    @BeforeAll
    public void setUp() {
        bookCategoryMapper = Mappers.getMapper(BookCategoryMapper.class);
        BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
        BorrowMapper borrowMapper = Mappers.getMapper(BorrowMapper.class);
        ReflectionTestUtils.setField(bookMapper, "borrowMapper", borrowMapper);
        ReflectionTestUtils.setField(bookCategoryMapper, "bookMapper", bookMapper);
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
}
