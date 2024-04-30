package com.app.library.util;

import com.app.library.cache.BookCacheManager;
import com.app.library.persistence.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

import static com.app.library.util.AuthorMockFactory.*;
import static com.app.library.util.BookCategoryMockFactory.getBookCategory;
import static com.app.library.util.BookMockFactory.getBook;
import static com.app.library.util.BookMockFactory.getSecondBook;
import static com.app.library.util.BorrowMockFactory.getBorrow;
import static com.app.library.util.BorrowMockFactory.getBorrows;
import static com.app.library.util.UserMockFactory.getReaderUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestConfiguration
public class TestMockConfiguration {

    @Bean
    @Primary
    public AuthorRepository mockAuthorRepository() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(getAuthor()));
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(Optional.of(getAuthor()));
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(getAuthor());
        Mockito.when(authorRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(authorRepository.findAllByFirstOrLastName(Mockito.any())).thenReturn(List.of(getAuthorWithNameAsFirstName()));

        return authorRepository;
    }

    @Bean
    @Primary
    public BookCategoryRepository mockBookCategoryRepository() {
        BookCategoryRepository bookCategoryRepository = Mockito.mock(BookCategoryRepository.class);
        Mockito.when(bookCategoryRepository.findAll()).thenReturn(List.of(getBookCategory()));
        Mockito.when(bookCategoryRepository.findById(Mockito.any())).thenReturn(Optional.of(getBookCategory()));
        Mockito.when(bookCategoryRepository.save(Mockito.any())).thenReturn(getBookCategory());
        Mockito.when(bookCategoryRepository.existsById(Mockito.any())).thenReturn(true);

        return bookCategoryRepository;
    }

    @Bean
    @Primary
    public BookRepository mockBookRepository() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(getBook()));
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(getSecondBook()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBook());
        Mockito.when(bookRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(bookRepository.findAllByTitle(any())).thenReturn(List.of(getBook()));

        return bookRepository;
    }

    @Bean
    @Primary
    public BorrowRepository mockBorrowRepository() {
        BorrowRepository borrowRepository = Mockito.mock(BorrowRepository.class);
        Mockito.when(borrowRepository.findAll()).thenReturn(List.of(getBorrow()));
        Mockito.when(borrowRepository.findById(Mockito.any())).thenReturn(Optional.of(getBorrow()));
        Mockito.when(borrowRepository.save(Mockito.any())).thenReturn(getBorrows());
        Mockito.when(borrowRepository.existsById(Mockito.any())).thenReturn(true);

        return borrowRepository;
    }

    @Bean
    @Primary
    public UserRepository mockUserRepository() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findAll()).thenReturn(List.of(getReaderUser()));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(getReaderUser()));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(getReaderUser());
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(true);

        return userRepository;
    }
}
