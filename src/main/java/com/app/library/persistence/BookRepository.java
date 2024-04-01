package com.app.library.persistence;

import com.app.library.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    List<BookEntity> findAllByTitle(String title);

    @Query("SELECT b FROM BookEntity b WHERE b.bookCategory.name = :category")
    List<BookEntity> findAllByCategory(String category);

    List<BookEntity> findAllByAuthorId(UUID authorId);

    @Query("SELECT b FROM BookEntity b WHERE " +
            "(CAST(:bookId AS uuid) IS NULL OR b.id = :bookId) AND " +
            "(:title IS NULL OR b.title = :title) AND " +
            "(:year IS NULL OR b.year = :year) AND " +
            "(CAST(:authorId AS uuid) IS NULL OR b.author.id = :authorId) AND " +
            "(CAST(:bookCategoryId AS uuid) IS NULL OR b.bookCategory.id = :bookCategoryId)")
    List<BookEntity> findAllByCriteria(@Param("bookId") UUID bookId,
                              @Param("title") String title,
                              @Param("year") Year year,
                              @Param("authorId") UUID authorId,
                              @Param("bookCategoryId") UUID bookCategoryId);

    @Override
    @Query("SELECT DISTINCT b FROM BookEntity b JOIN FETCH b.borrows")
    List<BookEntity> findAll();
}
