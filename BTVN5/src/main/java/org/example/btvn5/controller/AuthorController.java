package org.example.btvn5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.btvn5.dto.request.CreateAuthorRequest;
import org.example.btvn5.dto.request.UpdateAuthorRequest;
import org.example.btvn5.dto.response.AuthorResponse;
import org.example.btvn5.dto.response.BookResponse;
import org.example.btvn5.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return new ResponseEntity<>(authorService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAllAuthors(Pageable pageable) {
        return ResponseEntity.ok(authorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody UpdateAuthorRequest request) {
        return ResponseEntity.ok(authorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<Page<BookResponse>> getBooksByAuthor(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(authorService.findBooksByAuthor(id, pageable));
    }
}