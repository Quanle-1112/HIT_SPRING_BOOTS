package org.example.btvn5.service;

import org.example.btvn5.dto.request.CreateAuthorRequest;
import org.example.btvn5.dto.request.UpdateAuthorRequest;
import org.example.btvn5.dto.response.AuthorResponse;
import org.example.btvn5.dto.response.BookResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface AuthorService {
    Page<AuthorResponse> findAll(Pageable pageable);
    AuthorResponse findById(Long id);
    AuthorResponse create(CreateAuthorRequest request);
    AuthorResponse update(Long id, UpdateAuthorRequest request);
    void delete(Long id);
    Page<BookResponse> findBooksByAuthor(Long authorId, Pageable pageable);
}