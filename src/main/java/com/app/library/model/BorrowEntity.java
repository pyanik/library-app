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
    @Column(name = "borrow_id", nullable = false)
    private final String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private LocalDateTime dateOfBorrow;

    private LocalDateTime dateOfReturn;

    @Enumerated(EnumType.STRING)
    private BorrowStatus borrowStatus;
}
