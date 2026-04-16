package org.example.btvn5.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.btvn5.dto.request.CreateAuthorRequest;
import org.example.btvn5.dto.request.UpdateAuthorRequest;
import org.example.btvn5.dto.response.AuthorResponse;
import org.example.btvn5.dto.response.BookResponse;
import org.example.btvn5.entity.Author;
import org.example.btvn5.exception.BadRequestException;
import org.example.btvn5.exception.ConflictException;
import org.example.btvn5.exception.ResourceNotFoundException;
import org.example.btvn5.mapper.AppMapper;
import org.example.btvn5.repository.AuthorRepository;
import org.example.btvn5.repository.BookRepository;
import org.example.btvn5.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public Page<AuthorResponse> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).map(AppMapper::toAuthorResponse);
    }

    @Override
    public AuthorResponse findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tác giả với ID: " + id));
        return AppMapper.toAuthorResponse(author);
    }

    @Override
    public AuthorResponse create(CreateAuthorRequest request) {
        if (authorRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email đã tồn tại");
        }
        Author author = AppMapper.toAuthor(request);
        return AppMapper.toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponse update(Long id, UpdateAuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tác giả với ID: " + id));

        if (!author.getEmail().equals(request.getEmail()) && authorRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email đã tồn tại");
        }

        author.setName(request.getName());
        author.setEmail(request.getEmail());
        author.setPhone(request.getPhone());
        return AppMapper.toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tác giả với ID: " + id));

        if (bookRepository.existsByAuthorId(id)) {
            throw new BadRequestException("Không thể xóa. Tác giả này vẫn còn sách trong hệ thống.");
        }
        authorRepository.delete(author);
    }

    @Override
    public Page<BookResponse> findBooksByAuthor(Long authorId, Pageable pageable) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Không tìm thấy tác giả với ID: " + authorId);
        }
        return bookRepository.findByAuthorId(authorId, pageable).map(AppMapper::toBookResponse);
    }
}