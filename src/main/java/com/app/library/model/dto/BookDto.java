package com.app.library.model.dto;

import java.time.Year;
import java.util.List;
import java.util.UUID;

public record BookDto (
        UUID id,
        String title,
        String description,
        Year year,
        List<BorrowDto> borrows
) {}
