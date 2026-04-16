package org.example.btvn4.service;

import org.example.btvn4.dto.CreateProductRequest;
import org.example.btvn4.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product create(CreateProductRequest request);
    Product update(Long id, CreateProductRequest request);
    void delete(Long id);
    List<Product> searchByName(String keyword);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPriceRange(Double min, Double max);
}
