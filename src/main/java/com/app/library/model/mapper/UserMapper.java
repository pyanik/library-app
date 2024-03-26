package com.app.library.model.mapper;

import com.app.library.model.dto.UserDto;
import com.app.library.model.dto.UserInfoDto;
import com.app.library.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BorrowMapper.class)
public interface UserMapper {

    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "surname", target = "lastName")
    UserEntity toEntity(UserDto userDto);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surname")
    UserDto toDto(UserEntity userEntity);

    UserInfoDto toUserInfoDto(UserEntity userEntity);
}
