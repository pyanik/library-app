package com.app.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
class BookEntity {

    @Id
    @Column(name = "book_id", nullable = false)
    private final String id = UUID.randomUUID().toString();

    private String title;

    private String description;

    private Year year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id", nullable = false)
    private BookCategoryEntity bookCategory;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BorrowEntity> borrows;
}
