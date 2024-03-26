package com.app.library.service;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.model.entity.BookCategoryEntity;
import com.app.library.model.mapper.BookCategoryMapper;
import com.app.library.persistence.BookCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;
    private final BookCategoryMapper bookCategoryMapper;

    public List<BookCategoryDto> getAllBookCategories() {
        return bookCategoryRepository.findAll().stream()
                .map(bookCategoryMapper::toDto)
                .toList();
    }

    public Optional<BookCategoryDto> getBookCategoryById(UUID bookCategoryId) {
        return bookCategoryRepository.findById(bookCategoryId)
                .map(bookCategoryMapper::toDto);
    }

    public BookCategoryDto saveBookCategory(BookCategoryDto bookCategoryDto) {
        BookCategoryEntity bookCategoryToSave = bookCategoryMapper.toEntity(bookCategoryDto);
        BookCategoryEntity savedBookCategory = bookCategoryRepository.save(bookCategoryToSave);
        return bookCategoryMapper.toDto(savedBookCategory);

    }

    public Optional<BookCategoryDto> replaceBookCategory(UUID bookCategoryId, BookCategoryDto bookCategoryDto) {
        if (!bookCategoryRepository.existsById(bookCategoryId)) {
            return Optional.empty();
        }
        BookCategoryEntity bookCategoryToUpdate = bookCategoryMapper.toEntity(bookCategoryDto);
        BookCategoryEntity updatedBookCategory = bookCategoryRepository.save(bookCategoryToUpdate);
        return Optional.of(bookCategoryMapper.toDto(updatedBookCategory));
    }

    public void deleteBookCategory(UUID bookCategoryId) {
        bookCategoryRepository.deleteById(bookCategoryId);
    }
}
