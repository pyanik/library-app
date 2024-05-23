package com.app.library.controller;

import com.app.library.constant.ApplicationConstants;
import com.app.library.model.dto.AuthorDto;
import com.app.library.util.TestControllerUtil;
import com.app.library.util.TestMockConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.app.library.constant.TestConstants.AUTHOR_ID;
import static com.app.library.constant.TestConstants.AUTHOR_NAME;
import static com.app.library.util.AuthorMockFactory.getAuthorDto;
import static com.app.library.util.TestControllerUtil.getContent;
import static com.app.library.util.TestControllerUtil.mapResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(ApplicationConstants.ProfileName.TEST_PROFILE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestMockConfiguration.class})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class AuthorControllerTest {

    private static final String API_AUTHORS = "/api/authors";
    private static final String API_AUTHORS_BY_ID = "/api/authors/{id}";
    private static final String API_AUTHORS_SEARCH = "/api/authors/search";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllAuthorsTest_returnAuthors_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_AUTHORS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        List<AuthorDto> authors = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertFalse(authors.isEmpty());
    }

    @Test
    @SneakyThrows
    void getAuthorByIdTest_returnAuthor_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_AUTHORS_BY_ID, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url, AUTHOR_ID))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        AuthorDto author = mapResponse(AuthorDto.class, mvcResult);
        assertNotNull(author);
    }

    @Test
    @SneakyThrows
    void saveAuthorTest_authorSaved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_AUTHORS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(getAuthorDto())))
                .andExpect(status().isCreated())
                .andReturn();

        //then
        AuthorDto author = mapResponse(AuthorDto.class, mvcResult);
        assertNotNull(author);
    }

    @Test
    @SneakyThrows
    void deleteAuthorTest_authorRemoved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_AUTHORS_BY_ID, port);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .delete(url, AUTHOR_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void getAuthorsByNameOrSurname_allValidAuthorsReturned_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_AUTHORS_SEARCH, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .param("name", AUTHOR_NAME))
                .andDo(print())
                .andReturn();

        //then
        List<AuthorDto> authors = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertEquals(1, authors.size());
    }
}