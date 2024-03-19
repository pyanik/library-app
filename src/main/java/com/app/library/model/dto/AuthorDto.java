package com.app.library.model.dto;

import java.util.List;
import java.util.UUID;

public record AuthorDto (
        UUID id,
        String name,
        String surname,
        String biography,
        List<BookDto> books
) {}
