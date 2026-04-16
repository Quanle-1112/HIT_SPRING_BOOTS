package org.example.btvn4.service;

import org.apache.catalina.User;
import org.example.btvn4.dto.request.CreateUserRequest;
import org.example.btvn4.dto.request.UpdateUserRequest;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);
    User create(CreateUserRequest request);
    User update(Long id, UpdateUserRequest request);
    void delete(Long id);
}