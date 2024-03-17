package com.app.library.persistence;

import com.app.library.model.entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BorrowRepository extends JpaRepository<BorrowEntity, UUID> {

    List<BorrowEntity> findAllByUserId(UUID userId);
}
