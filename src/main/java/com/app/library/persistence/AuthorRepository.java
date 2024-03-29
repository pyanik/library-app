package com.app.library.persistence;

import com.app.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    List<AuthorEntity> findByFirstName(String firstName);

    List<AuthorEntity> findByLastName(String lastName);
}
