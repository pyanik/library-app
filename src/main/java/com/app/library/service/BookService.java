package com.app.library.service;

import com.app.library.cache.BookCacheManager;
import com.app.library.model.dto.BookDto;
import com.app.library.model.dto.BookSearchRequestDto;
import com.app.library.model.entity.BookEntity;
import com.app.library.model.mapper.BookMapper;
import com.app.library.persistence.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookCacheManager bookCacheManager;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public Optional<BookDto> getBookById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toDto);
    }

    public BookDto saveBook(BookDto bookDto) {
        BookEntity bookToSave = bookMapper.toEntity(bookDto);
        BookEntity savedBook = bookRepository.save(bookToSave);
        bookCacheManager.clearBookCache();
        return bookMapper.toDto(savedBook);
    }

    public Optional<BookDto> replaceBook(UUID bookId, BookDto bookDto) {
        if (!bookRepository.existsById(bookId)) {
            return Optional.empty();
        }
        BookEntity bookToUpdate = bookMapper.toEntity(bookDto);
        BookEntity updatedBook = bookRepository.save(bookToUpdate);
        return Optional.of(bookMapper.toDto(updatedBook));
    }

    @Async
    public void deleteBook(UUID bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookDto> getBooksByTitle(String title) {
        return bookCacheManager.getBooksByTitle(title).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookDto> getBooksByCategory(String category) {
        return bookRepository.findAllByCategory(category).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookDto> getBooksByAuthor(UUID authorId) {
        return bookRepository.findAllByAuthorId(authorId).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookDto> getBooksByCriteria(BookSearchRequestDto searchCriteria) {
        UUID bookId = searchCriteria.bookId();
        String title = searchCriteria.title();
        Year year = searchCriteria.year();
        UUID authorId = searchCriteria.authorId();
        UUID bookCategoryId = searchCriteria.bookCategoryId();
        return bookRepository.findAllByCriteria(bookId, title, year, authorId, bookCategoryId).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
