package com.app.library.model.dto;

import java.time.Year;
import java.util.UUID;

public record BookSearchRequestDto (
        UUID bookId,
        String title,
        Year year,
        UUID authorId,
        UUID bookCategoryId
) {}
