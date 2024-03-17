package com.app.library.model.mapper;

import com.app.library.model.dto.BookDto;
import com.app.library.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BorrowMapper.class)
public interface BookMapper {

    BookEntity toEntity(BookDto bookDto);

    BookDto toDto(BookEntity bookEntity);
}
