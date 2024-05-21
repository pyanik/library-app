package com.app.library.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record AuthorDto (
        UUID id,

        @NotBlank(message = "Author name must not be blank.")
        String name,

        @NotBlank(message = "Author surname must not be blank.")
        String surname,

        String biography,

        List<BookDto> books,

        int businessObjectVersion
) implements CommonDto {

        @Override
        public int getBusinessObjectVersion() {
                return businessObjectVersion;
        }
}
