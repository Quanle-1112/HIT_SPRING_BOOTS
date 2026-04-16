package org.example.btvn4.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.btvn4.dto.CreateCategoryRequest;
import org.example.btvn4.entity.Category;
import org.example.btvn4.exception.BadRequestException;
import org.example.btvn4.exception.DuplicateResourceException;
import org.example.btvn4.exception.ResourceNotFoundException;
import org.example.btvn4.repository.CategoryRepository;
import org.example.btvn4.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() { return categoryRepository.findAll(); }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Category với id: " + id));
    }

    @Override
    public Category create(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Tên Category đã tồn tại");
        }
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, CreateCategoryRequest request) {
        Category category = findById(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new BadRequestException("Không thể xóa Category đang chứa Product");
        }
        categoryRepository.delete(category);
    }
}