package org.example.btvn5.service;

import org.example.btvn5.dto.request.CreateBookRequest;
import org.example.btvn5.dto.request.UpdateBookRequest;
import org.example.btvn5.dto.response.BookResponse;
import org.example.btvn5.entity.BookCategory;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface BookService {
    Page<BookResponse> findAll(Pageable pageable);
    BookResponse findById(Long id);
    BookResponse create(CreateBookRequest request);
    BookResponse update(Long id, UpdateBookRequest request);
    void delete(Long id);
    BookResponse borrowBook(Long id);
    BookResponse returnBook(Long id);
    Page<BookResponse> search(String keyword, Pageable pageable);
    Page<BookResponse> findByCategory(BookCategory category, Pageable pageable);
}