package com.app.library.util;

import com.app.library.model.dto.UserDto;
import com.app.library.model.entity.UserEntity;
import com.app.library.model.enums.UserRole;

import static com.app.library.constant.TestConstants.*;

public class UserMockFactory {

    public static UserDto getReaderUserDto() {
        return new UserDto(USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_PASSWORD, UserRole.READER, BORROW_DTO_LIST, BUSINESS_VERSION);
    }

    public static UserEntity getReaderUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(USER_ID);
        userEntity.setFirstName(AUTHOR_FIRST_NAME);
        userEntity.setLastName(AUTHOR_LAST_NAME);
        userEntity.setEmail(USER_EMAIL);
        userEntity.setPassword(USER_PASSWORD);
        userEntity.setUserRole(UserRole.READER);
        userEntity.setBorrows(BORROW_LIST);

        return userEntity;
    }
}
