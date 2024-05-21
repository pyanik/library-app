package com.app.library.model.entity;

import com.app.library.model.enums.UserRole;

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
@Table(name = "user", schema = DB_SCHEMA)
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BorrowEntity> borrows = new ArrayList<>();

    @Version
    @Column(name = "business_object_version")
    private int businessObjectVersion;
}
