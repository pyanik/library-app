package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Entity
@Table(name = "book_category", schema = DB_SCHEMA)
@Data
class BookCategoryEntity {

    @Id
    @Column(name = "book_category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "book_category", cascade = CascadeType.ALL)
    private Set<BookEntity> books = new HashSet<>();
}
