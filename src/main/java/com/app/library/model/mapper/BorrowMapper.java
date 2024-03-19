package com.app.library.model.mapper;

import com.app.library.model.dto.BorrowDto;
import com.app.library.model.entity.BorrowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BorrowMapper {

    BorrowEntity toEntity(BorrowDto borrowDto);

    BorrowDto toDto(BorrowEntity borrowEntity);
}
