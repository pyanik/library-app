package com.app.library.persistence;

import com.app.library.model.entity.BookCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCategoryRepository extends JpaRepository<BookCategoryEntity, UUID> {

}
