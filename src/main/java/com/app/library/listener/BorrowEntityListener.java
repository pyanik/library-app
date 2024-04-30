package com.app.library.listener;

import com.app.library.model.entity.BorrowEntity;
import com.app.library.model.enums.BorrowStatus;
import com.app.library.util.LocalDateTimeWrapper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class BorrowEntityListener {

    private LocalDateTimeWrapper localDateTimeWrapper;

    @PostPersist
    public void postPersist(BorrowEntity entity) {
        log.info(String.format("new Borrow record has been saved in database: %s", entity.getId()));
    }

    @PreUpdate
    @PrePersist
    public void setBorrowStatus(BorrowEntity entity) {
        LocalDateTime localDateTimeNow = localDateTimeWrapper.getLocalDateTimeNow();
        if (BorrowStatus.RETURNED.equals(entity.getBorrowStatus())) {
            entity.setDateOfReturn(localDateTimeNow);
        } else if (BorrowStatus.BORROWED.equals(entity.getBorrowStatus())) {
            entity.setDateOfBorrow(localDateTimeNow);
        }
        log.info(String.format("status of the borrow: %s has been updated", entity.getId()));
    }
}
