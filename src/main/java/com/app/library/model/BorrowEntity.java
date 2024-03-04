package com.app.library.model;

import com.app.library.constant.BorrowStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "borrow")
@Data
class BorrowEntity {

    @Id
    @Column(name = "borrow_id")
    private final String id = UUID.randomUUID().toString();
    private BookEntity book;
    private UserEntity user;
    private LocalDateTime dateOfBorrow;
    private LocalDateTime dateOfReturn;
    @Enumerated(EnumType.STRING)
    private BorrowStatus borrowStatus;
}
