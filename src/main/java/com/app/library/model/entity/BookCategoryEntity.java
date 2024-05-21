package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "book_category", schema = DB_SCHEMA)
public class BookCategoryEntity {

    @Id
    @Column(name = "book_category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    private List<BookEntity> books = new ArrayList<>();

    @Version
    @Column(name = "business_object_version")
    private int businessObjectVersion;
}
