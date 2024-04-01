package com.app.library.persistence;

import com.app.library.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    @Override
    @Query("SELECT DISTINCT u FROM UserEntity u JOIN FETCH u.borrows")
    List<UserEntity> findAll();
}
