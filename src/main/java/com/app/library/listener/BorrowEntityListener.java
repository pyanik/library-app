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
        log.info(String.format("new Borrow record has been saved in database: %s", entity.getId()));
        if (BorrowStatus.RETURNED.equals(entity.getBorrowStatus())) {
            entity.setDateOfReturn(localDateTimeWrapper.getLocalDateTimeNow());
        } else if (BorrowStatus.BORROWED.equals(entity.getBorrowStatus())) {
            entity.setDateOfBorrow(localDateTimeWrapper.getLocalDateTimeNow());
        }
    }
}
