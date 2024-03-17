package com.app.library.controller;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.service.BookCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
            return ResponseEntity.ok(allBookCategories);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookCategoryDto> getBookCategoryById(@PathVariable UUID id) {
        return bookCategoryService.getBookCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BookCategoryDto> saveBookCategory(@Valid @RequestBody BookCategoryDto bookCategory) {
        BookCategoryDto savedBookCategory = bookCategoryService.saveBookCategory(bookCategory);
        URI savedBookCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBookCategory.id())
                .toUri();
        return ResponseEntity.created(savedBookCategoryUri).body(savedBookCategory);
    }

    @PutMapping("/{id}")
    ResponseEntity<BookCategoryDto> replaceBookCategory(@PathVariable UUID id, @Valid @RequestBody BookCategoryDto bookCategory) {
        return bookCategoryService.replaceBookCategory(id, bookCategory)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBookCategory(@PathVariable UUID id) {
        bookCategoryService.deleteBookCategory(id);
        return ResponseEntity.noContent().build();
    }
}
