package com.app.library.model.dto;

import java.util.List;
import java.util.UUID;

public record BookCategoryDto (
        UUID id,
        String name,
        String description,
        List<BookDto> books
) {}
