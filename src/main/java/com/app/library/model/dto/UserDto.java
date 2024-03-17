package com.app.library.model.dto;

import com.app.library.model.enums.UserRole;

import java.util.List;
import java.util.UUID;

public record UserDto (
        UUID id,
        String name,
        String surname,
        String email,
        String password,
        UserRole userRole,
        List<BorrowDto> borrows
) {}
