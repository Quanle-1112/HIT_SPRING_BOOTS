package org.example.btvn5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.btvn5.entity.BookCategory;
import org.example.btvn5.entity.BookStatus;

@Data
public class UpdateBookRequest {
    @NotBlank
    @Size(min = 2, max = 200)
    private String title;

    @NotNull
    private BookCategory category;

    @NotNull
    private BookStatus status;

    private Integer publishedYear;
}