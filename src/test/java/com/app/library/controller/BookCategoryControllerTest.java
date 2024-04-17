package com.app.library.controller;

import com.app.library.model.dto.BookCategoryDto;
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

import static com.app.library.constant.TestConstants.BOOK_CATEGORY_ID;
import static com.app.library.util.BookCategoryMockFactory.getBookCategoryDto;
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
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
class BookCategoryControllerTest {

    private static final String API_BOOK_CATEGORIES =  "/api/categories";
    private static final String API_BOOK_CATEGORIES_BY_ID =  "/api/categories/{id}";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllBookCategoriesTest_returnCategories_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOK_CATEGORIES, port);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        List<BookCategoryDto> categories = mapResponse(new TypeReference<>() {}, mvcResult);
        assertFalse(categories.isEmpty());
    }

    @Test
    @SneakyThrows
    void getBookCategoryByIdTest_returnCategory_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOK_CATEGORIES_BY_ID, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url, BOOK_CATEGORY_ID))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        BookCategoryDto category = mapResponse(BookCategoryDto.class, mvcResult);
        assertNotNull(category);
    }

    @Test
    @SneakyThrows
    void saveBookCategoryTest_categorySaved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOK_CATEGORIES, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(getBookCategoryDto())))
                .andExpect(status().isCreated())
                .andReturn();

        //then
        BookCategoryDto category = mapResponse(BookCategoryDto.class, mvcResult);
        assertNotNull(category);
    }

    @Test
    @SneakyThrows
    void deleteBookCategoryTest_categoryRemoved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BOOK_CATEGORIES_BY_ID, port);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .delete(url, BOOK_CATEGORY_ID))
                .andExpect(status().isNoContent());
    }
}
