package com.app.library.service;

import com.app.library.cache.AuthorCacheManager;
import com.app.library.cache.BookCacheManager;
import com.app.library.model.dto.AuthorDto;
import com.app.library.model.entity.AuthorEntity;
import com.app.library.model.mapper.AuthorMapper;
import com.app.library.persistence.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorCacheManager authorCacheManager;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

    public Optional<AuthorDto> getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId)
                .map(authorMapper::toDto);
    }

    public AuthorDto saveAuthor(AuthorDto authorDto) {
        AuthorEntity authorToSave = authorMapper.toEntity(authorDto);
        AuthorEntity savedAuthor = authorRepository.save(authorToSave);
        authorCacheManager.clearAuthorCache();
        return authorMapper.toDto(savedAuthor);

    }

    public Optional<AuthorDto> replaceAuthor(UUID authorId, AuthorDto authorDto) {
        if (!authorRepository.existsById(authorId)) {
            return Optional.empty();
        }
        AuthorEntity authorToUpdate = authorMapper.toEntity(authorDto);
        AuthorEntity updatedAuthor = authorRepository.save(authorToUpdate);
        return Optional.of(authorMapper.toDto(updatedAuthor));
    }

    public void deleteAuthor(UUID authorId) {
        authorRepository.deleteById(authorId);
    }

    public List<AuthorDto> getAuthorByName(String name) {
        return authorCacheManager.getAuthorsByName(name).stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
}
