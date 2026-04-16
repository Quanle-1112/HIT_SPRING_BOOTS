package org.example.btvn5.repository;

import org.example.btvn5.entity.Book;
import org.example.btvn5.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
    boolean existsByAuthorId(Long authorId);
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Book> findByCategory(BookCategory category, Pageable pageable);
}