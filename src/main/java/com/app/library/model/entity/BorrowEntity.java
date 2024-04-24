package com.app.library.model.entity;

import com.app.library.listener.BorrowEntityListener;
import com.app.library.model.enums.BorrowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "borrow", schema = DB_SCHEMA)
@EntityListeners(BorrowEntityListener.class)
public class BorrowEntity {

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

    @Column(name = "date_of_borrow", nullable = false)
    private LocalDateTime dateOfBorrow;

    @Column(name = "date_of_return", nullable = false)
    private LocalDateTime dateOfReturn;

    @Enumerated(EnumType.STRING)
    @Column(name = "borrow_status", nullable = false)
    private BorrowStatus borrowStatus;
}
