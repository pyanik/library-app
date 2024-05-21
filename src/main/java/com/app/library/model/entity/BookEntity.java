package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "book", schema = DB_SCHEMA)
public class BookEntity {

    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Year year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id", nullable = false)
    private BookCategoryEntity bookCategory;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BorrowEntity> borrows = new ArrayList<>();

    @Version
    @Column(name = "business_object_version")
    private int businessObjectVersion;
}
