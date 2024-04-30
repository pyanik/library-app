package com.app.library.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeWrapper {

    public LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
    }
}
