package com.app.library.cache;

import com.app.library.constant.ApplicationConstants;
import com.app.library.model.entity.BookEntity;
import com.app.library.persistence.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.app.library.constant.ApplicationConstants.CacheNames.BOOKS_CACHE;
import static com.app.library.constant.ApplicationConstants.CacheNames.BOOKS_CACHE_CLEARED_MESSAGE;

@Component
@AllArgsConstructor
@Slf4j
public class BookCacheManager {

    private final BookRepository bookRepository;

    @Cacheable(value = {BOOKS_CACHE}, key = "#title")
    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @CacheEvict(value = {BOOKS_CACHE}, allEntries = true)
    public void clearBookCache() {
        log.info(BOOKS_CACHE_CLEARED_MESSAGE);
    }
}
