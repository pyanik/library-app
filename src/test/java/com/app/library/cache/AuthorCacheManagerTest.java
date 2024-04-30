package com.app.library.cache;

import com.app.library.constant.TestConstants;
import com.app.library.persistence.AuthorRepository;
import com.app.library.util.TestMockConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = {TestMockConfiguration.class})
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class AuthorCacheManagerTest {

    @Autowired
    AuthorCacheManager authorCacheManager;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void givenAuthorsThatShouldBeCached_whenfindAllByFirstOrLastName_thenResultShouldBePutInCache() {
        //when
        authorCacheManager.getAuthorsByName(TestConstants.AUTHOR_NAME);
        authorCacheManager.getAuthorsByName(TestConstants.AUTHOR_NAME);

        //then
        verify(authorRepository, times(1)).findAllByFirstOrLastName(any());
    }
}
