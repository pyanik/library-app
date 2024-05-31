package com.app.library.controller;

import com.app.library.constant.ApplicationConstants;
import com.app.library.exception.EmailAlreadyExistsException;
import com.app.library.model.dto.UserDto;
import com.app.library.persistence.UserRepository;
import com.app.library.util.TestControllerUtil;
import com.app.library.util.TestMockConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.app.library.constant.ApplicationConstants.ProfileNames.TEST_PROFILE;
import static com.app.library.constant.TestConstants.USER_ID;
import static com.app.library.util.TestControllerUtil.getContent;
import static com.app.library.util.TestControllerUtil.mapResponse;
import static com.app.library.util.UserMockFactory.getReaderUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(TEST_PROFILE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestMockConfiguration.class})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class UserControllerTest {

    private static final String API_USERS = "/api/users";
    private static final String API_USERS_BY_ID = "/api/users/{id}";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @SneakyThrows
    void getAllUsersTest_returnUsers_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_USERS, port);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        List<UserDto> users = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertFalse(users.isEmpty());
    }

    @Test
    @SneakyThrows
    void getUserByIdTest_returnUser_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_USERS_BY_ID, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url, USER_ID))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        UserDto user = mapResponse(UserDto.class, mvcResult);
        assertNotNull(user);
    }

    @Test
    @SneakyThrows
    void saveUserTest_userSaved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_USERS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(getReaderUserDto())))
                .andExpect(status().isCreated())
                .andReturn();

        //then
        UserDto user = mapResponse(UserDto.class, mvcResult);
        assertNotNull(user);
    }

    @Test
    @SneakyThrows
    void deleteUserTest_userRemoved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_USERS_BY_ID, port);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .delete(url, USER_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void emailAlreadyExistsValidationTest_userNotSaved_failed() {
        //given
        String url = TestControllerUtil.getUrl(API_USERS, port);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(true);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(getReaderUserDto())))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmailAlreadyExistsException))
                .andExpect(result -> assertEquals("User with email address user.email@example.com already exists.", result.getResolvedException().getMessage()));
    }
}
