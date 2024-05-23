package com.app.library.cache;

import com.app.library.constant.ApplicationConstants;
import com.app.library.constant.TestConstants;
import com.app.library.persistence.BookRepository;
import com.app.library.util.TestMockConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles(ApplicationConstants.ProfileName.TEST_PROFILE)
@ContextConfiguration(classes = {TestMockConfiguration.class})
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class BookCacheManagerTest {

    @Autowired
    BookCacheManager bookCacheManager;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void givenBooksThatShouldBeCached_whenFindByTitle_thenResultShouldBePutInCache() {
        //when
        bookCacheManager.getBooksByTitle(TestConstants.BOOK_TITLE_1);
        bookCacheManager.getBooksByTitle(TestConstants.BOOK_TITLE_1);

        //then
        verify(bookRepository, times(1)).findAllByTitle(any());
    }
}
