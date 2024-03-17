package com.app.library.model.mapper;

import com.app.library.model.dto.AuthorDto;
import com.app.library.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class)
public interface AuthorMapper {

    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "surname", target = "lastName")
    AuthorEntity toEntity(AuthorDto authorDto);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surname")
    AuthorDto toDto(AuthorEntity authorEntity);
}
