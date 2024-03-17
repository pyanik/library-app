package com.app.library.model.dto;

import com.app.library.model.enums.BorrowStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BorrowDto (
        UUID id,
        LocalDateTime dateOfBorrow,
        LocalDateTime dateOfReturn,
        BorrowStatus borrowStatus
) {}
