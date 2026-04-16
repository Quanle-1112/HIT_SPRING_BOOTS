package org.example.btvn5.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}