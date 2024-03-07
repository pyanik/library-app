package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "book_category", schema = DB_SCHEMA)
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
