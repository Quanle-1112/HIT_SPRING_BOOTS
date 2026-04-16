package org.example.btvn5.dto.response;

import lombok.Data;
import org.example.btvn5.entity.BookCategory;
import org.example.btvn5.entity.BookStatus;

import java.time.LocalDateTime;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private BookCategory category;
    private BookStatus status;
    private Integer totalCopies;
    private Integer availableCopies;
    private Integer publishedYear;
    private Long authorId;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}