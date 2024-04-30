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

@Component
@AllArgsConstructor
@Slf4j
public class BookCacheManager {

    private final BookRepository bookRepository;

    @Cacheable(value = {ApplicationConstants.CacheNames.BOOKS_CACHE}, key = "#title")
    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @CacheEvict(value = {ApplicationConstants.CacheNames.BOOKS_CACHE}, allEntries = true)
    public void clearBookCache() {
        log.info("Books cache has been cleared.");
    }
}
