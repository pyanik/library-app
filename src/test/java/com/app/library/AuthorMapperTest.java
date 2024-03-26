package com.app.library;

import com.app.library.model.dto.AuthorDto;
import com.app.library.model.entity.AuthorEntity;
import com.app.library.model.mapper.AuthorMapper;
import com.app.library.model.mapper.BookMapper;
import com.app.library.model.mapper.BorrowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorMapperTest {

    private AuthorMapper authorMapper;

    @BeforeAll
    public void setUp() {
        authorMapper = Mappers.getMapper(AuthorMapper.class);
        BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
        BorrowMapper borrowMapper = Mappers.getMapper(BorrowMapper.class);
        ReflectionTestUtils.setField(bookMapper, "borrowMapper", borrowMapper);
        ReflectionTestUtils.setField(authorMapper, "bookMapper", bookMapper);
    }

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
}

