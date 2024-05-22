package com.app.library.model.dto;

import com.app.library.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UserDto (
        UUID id,

        @NotBlank(message = "User name must not be blank.")
        String name,

        @NotBlank(message = "Author surname must not be blank.")
        String surname,

        @NotBlank(message = "Email address must not be blank.")
        @Email(message = "Email in wrong format.")
        String email,

        @NotBlank(message = "Password must not be blank.")
        String password,

        @NotNull(message = "User role must not be blank.")
        UserRole userRole,

        List<BorrowDto> borrows,

        int businessObjectVersion
) implements DomainDto {

        @Override
        public int getBusinessObjectVersion() {
                return businessObjectVersion;
        }
}
