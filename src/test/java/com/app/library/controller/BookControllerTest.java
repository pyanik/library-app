package com.app.library.controller;

import com.app.library.model.dto.BookDto;
import com.app.library.util.TestControllerUtil;
import com.app.library.util.TestMockConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static com.app.library.constant.TestConstants.BOOK_ID_2;
import static com.app.library.util.BookMockFactory.getBookDto;
import static com.app.library.util.TestControllerUtil.getContent;
import static com.app.library.util.TestControllerUtil.mapResponse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("unit-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestMockConfiguration.class})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class BookControllerTest {

    private static final String API_BOOKS = "/api/books";
    private static final String API_BOOKS_BY_ID = "/api/books/{id}";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllBookTest_returnBooks_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOKS, port);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        List<BookDto> book = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertFalse(book.isEmpty());
    }

    @Test
    @SneakyThrows
    void getBookByIdTest_returnBook_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOKS_BY_ID, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url, BOOK_ID_2))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        BookDto book = mapResponse(BookDto.class, mvcResult);
        assertNotNull(book);
    }

    @Test
    @SneakyThrows
    void saveBookTest_bookSaved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOKS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(getBookDto())))
                .andExpect(status().isCreated())
                .andReturn();

        //then
        BookDto book = mapResponse(BookDto.class, mvcResult);
        assertNotNull(book);
    }

    @Test
    @SneakyThrows
    void deleteBookTest_bookRemoved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOKS_BY_ID, port);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .delete(url, BOOK_ID_2))
                .andExpect(status().isNoContent());
    }
}
