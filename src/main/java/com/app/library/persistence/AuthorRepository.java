package com.app.library.persistence;

import com.app.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    @Query("SELECT a FROM AuthorEntity a WHERE " +
            "a.firstName = :name OR " +
            "a.lastName = :name")
    List<AuthorEntity> findAllByFirstOrLastName(String name);
}
