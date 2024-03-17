package com.app.library.controller;

import com.app.library.model.dto.AuthorDto;
import com.app.library.service.AuthorService;
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
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        if (!allAuthors.isEmpty()) {
            return ResponseEntity.ok(allAuthors);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> getAuthorById(@PathVariable UUID id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<AuthorDto> saveAuthor(@Valid @RequestBody AuthorDto author) {
        AuthorDto savedAuthor = authorService.saveAuthor(author);
        URI savedAuthorUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.id())
                .toUri();
        return ResponseEntity.created(savedAuthorUri).body(savedAuthor);
    }

    @PutMapping("/{id}")
    ResponseEntity<AuthorDto> replaceAuthor(@PathVariable UUID id, @Valid @RequestBody AuthorDto author) {
        return authorService.replaceAuthor(id, author)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    ResponseEntity<List<AuthorDto>> getAuthorsByNameOrSurname(@RequestParam String name) {
        List<AuthorDto> allAuthorsByName = authorService.getAuthorByName(name);
        if (!allAuthorsByName.isEmpty()) {
            return ResponseEntity.ok(allAuthorsByName);
        }
        return ResponseEntity.noContent().build();
    }
}
