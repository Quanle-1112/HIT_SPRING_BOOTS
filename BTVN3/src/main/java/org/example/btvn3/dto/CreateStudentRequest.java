package org.example.btvn3.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateStudentRequest {
    @NotBlank(message = "Mã sinh viên không được để trống")
    @Pattern(regexp = "^SV\\d{3}$", message = "Mã sinh viên phải có định dạng SVxxx (VD: SV001)")
    private String studentCode;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0")
    private String phone;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải ở trong quá khứ")
    private LocalDate dateOfBirth;

    @NotNull(message = "GPA không được để trống")
    @DecimalMin(value = "0.0", message = "GPA tối thiểu là 0.0")
    @DecimalMax(value = "4.0", message = "GPA tối đa là 4.0")
    private Double gpa;

    @NotBlank(message = "Ngành học không được để trống")
    private String major;

    @NotNull(message = "Năm học không được để trống")
    @Min(value = 1, message = "Năm học nhỏ nhất là 1")
    @Max(value = 6, message = "Năm học lớn nhất là 6")
    private Integer year;
}