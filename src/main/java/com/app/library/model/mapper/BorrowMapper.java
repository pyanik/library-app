package com.app.library.model.mapper;

import com.app.library.model.dto.BorrowDto;
import com.app.library.model.entity.BorrowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BorrowMapper {

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    BorrowEntity toEntity(BorrowDto borrowDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    BorrowDto toDto(BorrowEntity borrowEntity);
}
