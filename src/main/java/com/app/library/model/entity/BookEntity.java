package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Set;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Entity
@Table(name = "book", schema = DB_SCHEMA)
@Getter
@Setter
class BookEntity {

    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String title;

    @Column(name = "name")
    private String description;

    @Column(name = "name")
    private Year year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id", nullable = false)
    private BookCategoryEntity bookCategory;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BorrowEntity> borrows;
}
