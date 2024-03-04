package com.app.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book_category")
@Data
class BookCategoryEntity {

    @Id
    @Column(name = "book_category_id", nullable = false)
    private final String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    @OneToMany(mappedBy = "book_category", cascade = CascadeType.ALL)
    private Set<BookEntity> books = new HashSet<>();
}
