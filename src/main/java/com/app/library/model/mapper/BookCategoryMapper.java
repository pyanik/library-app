package com.app.library.model.mapper;

import com.app.library.model.dto.BookCategoryDto;
import com.app.library.model.entity.BookCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class)
public interface BookCategoryMapper {

    BookCategoryEntity toEntity(BookCategoryDto bookCategoryDto);

    BookCategoryDto toDto(BookCategoryEntity bookCategoryEntity);
}
