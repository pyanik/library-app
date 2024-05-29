package com.app.library.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.Year;
import java.util.List;
import java.util.UUID;

public record BookDto (
        UUID id,

        @NotBlank(message = "Book title must not be blank.")
        String title,

        String description,

        Year year,

        List<BorrowDto> borrows,

        int businessObjectVersion
) implements BusinessObjectVersionDto {}
