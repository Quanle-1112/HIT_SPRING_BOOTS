package org.example.btvn5.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.btvn5.entity.BookCategory;

@Data
public class CreateBookRequest {
    @NotBlank
    @Size(min = 2, max = 200)
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private BookCategory category;

    @NotNull
    @Min(1)
    private Integer totalCopies;

    private Integer publishedYear;

    @NotNull
    private Long authorId;
}