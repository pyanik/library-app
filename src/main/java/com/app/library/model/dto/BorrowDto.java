package com.app.library.model.dto;

import com.app.library.model.enums.BorrowStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record BorrowDto (
        UUID id,

        @NotBlank(message = "Book id must not be blank.")
        UUID bookId,

        @NotBlank(message = "User id must not be blank.")
        UUID userId,

        LocalDateTime dateOfBorrow,

        LocalDateTime dateOfReturn,

        BorrowStatus borrowStatus
) {}
