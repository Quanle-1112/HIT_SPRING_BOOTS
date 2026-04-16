package org.example.btvn4.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.example.btvn4.dto.request.CreateUserRequest;
import org.example.btvn4.dto.request.UpdateUserRequest;
import org.example.btvn4.exception.DuplicateResourceException;
import org.example.btvn4.exception.ResourceNotFoundException;
import org.example.btvn4.repository.UserRepository;
import org.example.btvn4.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy User với id: " + id));
    }

    @Override
    public User create(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username '" + request.getUsername() + "' đã tồn tại");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email '" + request.getEmail() + "' đã được sử dụng");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .active(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, UpdateUserRequest request) {
        User user = findById(id);

        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email '" + request.getEmail() + "' đã được sử dụng bởi tài khoản khác");
        }

        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        if (request.getActive() != null) {
            user.setActive(request.getActive());
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}