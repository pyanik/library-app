package com.app.library.configuration;

import com.app.library.constant.ApplicationConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(ApplicationConstants.CacheNames.BOOKS_CACHE, ApplicationConstants.CacheNames.AUTHORS_CACHE);
    }
}
