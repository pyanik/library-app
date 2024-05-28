package com.app.library.controller;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.service.BookCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.ControllerMessages.BOOK_CATEGORY_HAS_BEEN_DELETED;

@RestController
@Validated
@RequestMapping("/api/categories")
@AllArgsConstructor
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @GetMapping
    ResponseEntity<List<BookCategoryDto>> getAllBookCategories() {
        List<BookCategoryDto> allBookCategories = bookCategoryService.getAllBookCategories();
        if (!allBookCategories.isEmpty()) {
            return new ResponseEntity<>(allBookCategories, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookCategoryDto> getBookCategoryById(@PathVariable UUID id) {
        return bookCategoryService.getBookCategoryById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BookCategoryDto> saveBookCategory(@Valid @RequestBody BookCategoryDto bookCategory) {
        BookCategoryDto savedBookCategory = bookCategoryService.saveBookCategory(bookCategory);
        return new ResponseEntity<>(savedBookCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<BookCategoryDto> replaceBookCategory(@PathVariable UUID id, @Valid @RequestBody BookCategoryDto bookCategory) {
        return bookCategoryService.replaceBookCategory(id, bookCategory)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBookCategory(@PathVariable UUID id) {
        bookCategoryService.deleteBookCategory(id);
        return new ResponseEntity<>(BOOK_CATEGORY_HAS_BEEN_DELETED, HttpStatus.NO_CONTENT);
    }
}
