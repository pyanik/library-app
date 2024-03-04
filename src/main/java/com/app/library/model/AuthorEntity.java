package com.app.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
@Data
class AuthorEntity {

    @Id
    @Column(name = "author_id", nullable = false)
    private final String id = UUID.randomUUID().toString();

    private String firstName;

    private String lastName;

    private String biography;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<BookEntity> books;
}
