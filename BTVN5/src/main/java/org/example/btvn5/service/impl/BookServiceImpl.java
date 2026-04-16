package org.example.btvn5.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.btvn5.dto.request.CreateBookRequest;
import org.example.btvn5.dto.request.UpdateBookRequest;
import org.example.btvn5.dto.response.BookResponse;
import org.example.btvn5.entity.Author;
import org.example.btvn5.entity.Book;
import org.example.btvn5.entity.BookCategory;
import org.example.btvn5.entity.BookStatus;
import org.example.btvn5.exception.BadRequestException;
import org.example.btvn5.exception.ConflictException;
import org.example.btvn5.exception.ResourceNotFoundException;
import org.example.btvn5.mapper.AppMapper;
import org.example.btvn5.repository.AuthorRepository;
import org.example.btvn5.repository.BookRepository;
import org.example.btvn5.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(AppMapper::toBookResponse);
    }

    @Override
    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));
        return AppMapper.toBookResponse(book);
    }

    @Override
    public BookResponse create(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new ConflictException("ISBN đã tồn tại");
        }

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tác giả với ID: " + request.getAuthorId()));

        Book book = AppMapper.toBook(request);
        book.setAuthor(author);
        book.setStatus(BookStatus.AVAILABLE);

        return AppMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse update(Long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));

        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setPublishedYear(request.getPublishedYear());
        book.setStatus(request.getStatus());

        return AppMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));

        if (book.getAvailableCopies() < book.getTotalCopies()) {
            throw new BadRequestException("Không thể xóa. Đang có người mượn sách này.");
        }
        bookRepository.delete(book);
    }

    @Override
    public BookResponse borrowBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));

        if (book.getStatus() == BookStatus.DISCONTINUED) {
            throw new BadRequestException("Sách đã ngừng lưu hành");
        }
        if (book.getAvailableCopies() == 0) {
            throw new BadRequestException("Sách hiện tại đã hết");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        if (book.getAvailableCopies() == 0) {
            book.setStatus(BookStatus.OUT_OF_STOCK);
        }

        return AppMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse returnBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));

        if (book.getAvailableCopies().equals(book.getTotalCopies())) {
            throw new BadRequestException("Sách đã đủ, không thể trả thêm");
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        if (book.getStatus() == BookStatus.OUT_OF_STOCK) {
            book.setStatus(BookStatus.AVAILABLE);
        }

        return AppMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public Page<BookResponse> search(String keyword, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword, pageable).map(AppMapper::toBookResponse);
    }

    @Override
    public Page<BookResponse> findByCategory(BookCategory category, Pageable pageable) {
        return bookRepository.findByCategory(category, pageable).map(AppMapper::toBookResponse);
    }
}