package com.app.library.controller;

import com.app.library.model.dto.BookDto;
import com.app.library.model.dto.BookSearchRequestDto;
import com.app.library.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = bookService.getAllBooks();
        if (!allBooks.isEmpty()) {
            return new ResponseEntity<>(allBooks, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BookDto> saveBook(@Valid @RequestBody BookDto book) {
        BookDto savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<BookDto> replaceBook(@PathVariable UUID id, @Valid @RequestBody BookDto book) {
        return bookService.replaceBook(id, book)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book has been deleted.", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    ResponseEntity<List<BookDto>> searchBooksByQueryParams(@RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "category", required = false) String category,
                                              @RequestParam(value = "authorId", required = false) UUID id) {
        List<BookDto> searchedBooks = new ArrayList<>();

        if (title != null) {
            searchedBooks = bookService.getBooksByTitle(title);
        } else if (category != null) {
            searchedBooks = bookService.getBooksByCategory(category);
        } else if (id != null) {
            searchedBooks = bookService.getBooksByAuthor(id);
        }

        if (!searchedBooks.isEmpty()) {
            return new ResponseEntity<>(searchedBooks, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/search", consumes = "application/json")
    ResponseEntity<List<BookDto>> searchBooksByRequestBody(@RequestBody BookSearchRequestDto searchRequest) {
        List<BookDto> searchedBooks = bookService.getBooksByCriteria(searchRequest);

        if (!searchedBooks.isEmpty()) {
            return new ResponseEntity<>(searchedBooks, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
