package com.app.library.controller;

import com.app.library.constant.ApplicationConstants;
import com.app.library.exception.BookAlreadyBorrowedException;
import com.app.library.exception.EmailAlreadyExistsException;
import com.app.library.model.dto.BorrowDto;
import com.app.library.persistence.BookRepository;
import com.app.library.service.BorrowService;
import com.app.library.util.TestControllerUtil;
import com.app.library.util.TestMockConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
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
import java.util.Optional;

import static com.app.library.constant.TestConstants.BORROW_ID_1;
import static com.app.library.util.BookMockFactory.getBorrowedBook;
import static com.app.library.util.BorrowMockFactory.getBorrowDto;
import static com.app.library.util.TestControllerUtil.getContent;
import static com.app.library.util.TestControllerUtil.mapResponse;
import static com.app.library.util.UserMockFactory.getReaderUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(ApplicationConstants.ProfileName.TEST_PROFILE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestMockConfiguration.class})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class BorrowControllerTest {

    private static final String API_BORROWS = "/api/borrows";
    private static final String API_BORROWS_BY_ID = "/api/borrows/{id}";

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @SneakyThrows
    void getAllBorrowsTest_returnBorrows_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BORROWS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        List<BorrowDto> borrows = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertFalse(borrows.isEmpty());
    }

    @Test
    @SneakyThrows
    void getBorrowByIdTest_returnBorrow_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BORROWS_BY_ID, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(url, BORROW_ID_1))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        //then
        BorrowDto borrow = mapResponse(BorrowDto.class, mvcResult);
        assertNotNull(borrow);
    }

    @Test
    @SneakyThrows
    void saveBorrowsTest_borrowSaved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BORROWS, port);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(List.of(getBorrowDto()))))
                .andExpect(status().isCreated())
                .andReturn();

        //then
        List<BorrowDto> borrows = mapResponse(new TypeReference<>() {
        }, mvcResult);
        assertFalse(borrows.isEmpty());
    }

    @Test
    @SneakyThrows
    void deleteBorrowTest_borrowRemoved_passed() {
        //given
        String url = TestControllerUtil.getUrl(API_BORROWS_BY_ID, port);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                .delete(url, BORROW_ID_1))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void bookAlreadyBorrowedValidationTest_borrowNotSaved_failed() {
        //given
        String url = TestControllerUtil.getUrl(API_BORROWS, port);
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(getBorrowedBook()));

        mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(List.of(getBorrowDto()))))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookAlreadyBorrowedException))
                .andExpect(result -> assertEquals("The book is already borrowed.", result.getResolvedException().getMessage()));
    }
}
