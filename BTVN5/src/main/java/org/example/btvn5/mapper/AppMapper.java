package org.example.btvn5.mapper;

import org.example.btvn5.dto.request.CreateAuthorRequest;
import org.example.btvn5.dto.request.CreateBookRequest;
import org.example.btvn5.dto.response.AuthorResponse;
import org.example.btvn5.dto.response.BookResponse;
import org.example.btvn5.entity.Author;
import org.example.btvn5.entity.Book;

public class AppMapper {

    public static Author toAuthor(CreateAuthorRequest req) {
        Author author = new Author();
        author.setName(req.getName());
        author.setEmail(req.getEmail());
        author.setPhone(req.getPhone());
        return author;
    }

    public static AuthorResponse toAuthorResponse(Author author) {
        if (author == null) return null;
        AuthorResponse res = new AuthorResponse();
        res.setId(author.getId());
        res.setName(author.getName());
        res.setEmail(author.getEmail());
        res.setPhone(author.getPhone());
        res.setCreatedAt(author.getCreatedAt());
        res.setUpdatedAt(author.getUpdatedAt());
        return res;
    }

    public static Book toBook(CreateBookRequest req) {
        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setIsbn(req.getIsbn());
        book.setCategory(req.getCategory());
        book.setTotalCopies(req.getTotalCopies());
        book.setPublishedYear(req.getPublishedYear());
        return book;
    }

    public static BookResponse toBookResponse(Book book) {
        if (book == null) return null;
        BookResponse res = new BookResponse();
        res.setId(book.getId());
        res.setTitle(book.getTitle());
        res.setIsbn(book.getIsbn());
        res.setCategory(book.getCategory());
        res.setStatus(book.getStatus());
        res.setTotalCopies(book.getTotalCopies());
        res.setAvailableCopies(book.getAvailableCopies());
        res.setPublishedYear(book.getPublishedYear());
        res.setCreatedAt(book.getCreatedAt());
        res.setUpdatedAt(book.getUpdatedAt());

        if (book.getAuthor() != null) {
            res.setAuthorId(book.getAuthor().getId());
            res.setAuthorName(book.getAuthor().getName());
        }
        return res;
    }
}