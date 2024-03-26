package com.app.library.model.dto;

import com.app.library.model.enums.UserRole;

import java.util.List;
import java.util.UUID;

public record UserInfoDto(
        UUID id,

        String name,

        String surname,

        UserRole userRole,

        List<BorrowDto> borrows
) {}
