package com.app.library.cache;

import com.app.library.constant.ApplicationConstants;
import com.app.library.model.entity.AuthorEntity;
import com.app.library.model.entity.BookEntity;
import com.app.library.persistence.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AuthorCacheManager {

    private final AuthorRepository authorRepository;

    @Cacheable(value = {ApplicationConstants.CacheNames.AUTHORS_CACHE}, key = "#name")
    public List<AuthorEntity> getAuthorsByName(String name) {
        return authorRepository.findAllByFirstOrLastName(name);
    }

    @CacheEvict(value = {ApplicationConstants.CacheNames.AUTHORS_CACHE}, allEntries = true)
    public void clearAuthorCache() {
        log.info("Authors cache has been cleared.");
    }
}
