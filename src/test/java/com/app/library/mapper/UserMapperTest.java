package com.app.library.mapper;

import com.app.library.model.dto.UserDto;
import com.app.library.model.entity.UserEntity;
import com.app.library.model.mapper.BorrowMapper;
import com.app.library.model.mapper.UserMapper;
import com.app.library.util.UserMockFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeAll
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
        BorrowMapper borrowMapper = Mappers.getMapper(BorrowMapper.class);
        ReflectionTestUtils.setField(userMapper, "borrowMapper", borrowMapper);
    }

    @Test
    public void mapUserDtoToEntityTest() {
        //given
        UserDto readerUserDto = UserMockFactory.getReaderUserDto();

        //when
        UserEntity readerUser = userMapper.toEntity(readerUserDto);

        //then
        Assertions.assertNotNull(readerUser);
        Assertions.assertEquals(readerUserDto.id(), readerUser.getId());
        Assertions.assertEquals(readerUserDto.name(), readerUser.getFirstName());
        Assertions.assertEquals(readerUserDto.surname(), readerUser.getLastName());
        Assertions.assertEquals(readerUserDto.email(), readerUser.getEmail());
        Assertions.assertEquals(readerUserDto.password(), readerUser.getPassword());
        Assertions.assertEquals(readerUserDto.userRole(), readerUser.getUserRole());
        Assertions.assertEquals(readerUserDto.borrows().get(0).dateOfBorrow(), readerUser.getBorrows().get(0).getDateOfBorrow());
        Assertions.assertEquals(readerUserDto.borrows().get(0).dateOfReturn(), readerUser.getBorrows().get(0).getDateOfReturn());
        Assertions.assertEquals(readerUserDto.borrows().get(0).borrowStatus(), readerUser.getBorrows().get(0).getBorrowStatus());
        Assertions.assertEquals(readerUserDto.borrows().get(1).dateOfBorrow(), readerUser.getBorrows().get(1).getDateOfBorrow());
        Assertions.assertNull(readerUserDto.borrows().get(1).dateOfReturn());
        Assertions.assertEquals(readerUserDto.borrows().get(1).borrowStatus(), readerUser.getBorrows().get(1).getBorrowStatus());
    }

    @Test
    public void mapUserEntityToDtoTest() {
        //given
        UserEntity readerUser = UserMockFactory.getReaderUser();

        //when
        UserDto readerUserDto = userMapper.toDto(readerUser);

        //then
        Assertions.assertNotNull(readerUserDto);
        Assertions.assertEquals(readerUser.getId(), readerUserDto.id());
        Assertions.assertEquals(readerUser.getFirstName(), readerUserDto.name());
        Assertions.assertEquals(readerUser.getLastName(), readerUserDto.surname());
        Assertions.assertEquals(readerUser.getEmail(), readerUserDto.email());
        Assertions.assertEquals(readerUser.getPassword(), readerUserDto.password());
        Assertions.assertEquals(readerUser.getUserRole(), readerUserDto.userRole());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getDateOfBorrow(), readerUserDto.borrows().get(0).dateOfBorrow());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getDateOfReturn(), readerUserDto.borrows().get(0).dateOfReturn());
        Assertions.assertEquals(readerUser.getBorrows().get(0).getBorrowStatus(), readerUserDto.borrows().get(0).borrowStatus());
        Assertions.assertEquals(readerUser.getBorrows().get(1).getDateOfBorrow(), readerUserDto.borrows().get(1).dateOfBorrow());
        Assertions.assertNull(readerUser.getBorrows().get(1).getDateOfReturn());
        Assertions.assertEquals(readerUser.getBorrows().get(1).getBorrowStatus(), readerUserDto.borrows().get(1).borrowStatus());
    }
}
