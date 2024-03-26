package com.app.library.controller;

import com.app.library.model.dto.AuthorDto;
import com.app.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        if (!allAuthors.isEmpty()) {
            return new ResponseEntity<>(allAuthors, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> getAuthorById(@PathVariable UUID id) {
        return authorService.getAuthorById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<AuthorDto> saveAuthor(@Valid @RequestBody AuthorDto author) {
        AuthorDto savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<AuthorDto> replaceAuthor(@PathVariable UUID id, @Valid @RequestBody AuthorDto author) {
        return authorService.replaceAuthor(id, author)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author has been deleted.", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    ResponseEntity<List<AuthorDto>> getAuthorsByNameOrSurname(@RequestParam String name) {
        List<AuthorDto> allAuthorsByName = authorService.getAuthorByName(name);
        if (!allAuthorsByName.isEmpty()) {
            return new ResponseEntity<>(allAuthorsByName, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
