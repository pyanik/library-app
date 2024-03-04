package com.app.library.model.entity;

import com.app.library.model.enums.BorrowStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Entity
@Table(name = "borrow", schema = DB_SCHEMA)
@Getter
@Setter
class BorrowEntity {

    @Id
    @Column(name = "borrow_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
