package org.example.btvn4.service;

import org.example.btvn4.dto.CreateCategoryRequest;
import org.example.btvn4.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category create(CreateCategoryRequest request);
    Category update(Long id, CreateCategoryRequest request);
    void delete(Long id);
}
