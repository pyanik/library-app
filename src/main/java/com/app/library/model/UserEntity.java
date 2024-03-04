package com.app.library.model;

import com.app.library.constant.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
class UserEntity {

    @Id
    @Column(name = "user_id")
    private final String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<BorrowEntity> books;
}
