package com.app.library.listener;

import com.app.library.model.entity.BorrowEntity;
import com.app.library.model.enums.BorrowStatus;
import com.app.library.util.LocalDateTimeWrapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.app.library.constant.TestConstants.BORROW_ID_1;
import static com.app.library.util.BookMockFactory.getBook;
import static com.app.library.util.UserMockFactory.getReaderUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BorrowEntityListenerTest {

    @Mock
    private LocalDateTimeWrapper localDateTimeWrapper;

    @InjectMocks
    private BorrowEntityListener borrowListener;

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(BorrowStatus.BORROWED), Arguments.of(BorrowStatus.RETURNED));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void setBorrowStatusTest_statusChanged_passed(BorrowStatus borrowStatus) {
        //given
        LocalDateTime now = LocalDateTime.now();
        Mockito.when(localDateTimeWrapper.getLocalDateTimeNow()).thenReturn(now);

        BorrowEntity borrow = new BorrowEntity();
        borrow.setId(BORROW_ID_1);
        borrow.setBook(getBook());
        borrow.setUser(getReaderUser());
        borrow.setBorrowStatus(borrowStatus);

        //when
        borrowListener.setBorrowStatus(borrow);

        //then
        if (BorrowStatus.RETURNED.equals(borrowStatus)) {
            assertEquals(now, borrow.getDateOfReturn());
        } else if (BorrowStatus.BORROWED.equals(borrowStatus)) {
            assertEquals(now, borrow.getDateOfBorrow());
        }
    }
}