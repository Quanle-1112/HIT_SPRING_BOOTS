package org.example.btvn4.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 100, message = "Tên sản phẩm từ 2 đến 100 ký tự")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer quantity;

    private String description;

    @NotNull(message = "Category ID không được để trống")
    private Long categoryId;
}