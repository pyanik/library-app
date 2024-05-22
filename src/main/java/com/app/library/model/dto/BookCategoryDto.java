package com.app.library.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record BookCategoryDto (
        UUID id,

        @NotBlank(message = "Category name must not be blank.")
        String name,

        String description,

        List<BookDto> books,

        int businessObjectVersion
) implements DomainDto {

        @Override
        public int getBusinessObjectVersion() {
                return businessObjectVersion;
        }
}
