package com.app.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.app.library.constant.ApplicationConstants.DB_SCHEMA;

@Entity
@Table(name = "author", schema = DB_SCHEMA)
@Getter
@Setter
class AuthorEntity {

    @Id
    @Column(name = "author_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    private String lastName;

    private String biography;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookEntity> books = new ArrayList<>();
}
