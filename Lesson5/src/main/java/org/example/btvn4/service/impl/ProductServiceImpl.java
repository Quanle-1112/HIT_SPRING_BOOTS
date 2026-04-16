package org.example.btvn4.service.impl;



import lombok.RequiredArgsConstructor;
import org.example.btvn4.dto.CreateProductRequest;
import org.example.btvn4.entity.Category;
import org.example.btvn4.entity.Product;
import org.example.btvn4.exception.DuplicateResourceException;
import org.example.btvn4.exception.ResourceNotFoundException;
import org.example.btvn4.repository.CategoryRepository;
import org.example.btvn4.repository.ProductRepository;
import org.example.btvn4.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() { return productRepository.findAll(); }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Product với id: " + id));
    }

    @Override
    public Product create(CreateProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category không tồn tại"));

        if (productRepository.existsByNameAndCategory_Id(request.getName(), category.getId())) {
            throw new DuplicateResourceException("Tên Product bị trùng trong cùng Category");
        }

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .active(true)
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, CreateProductRequest request) {
        Product product = findById(id);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category không tồn tại"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> searchByName(String keyword) { return productRepository.findByNameContaining(keyword); }

    @Override
    public List<Product> findByCategoryId(Long categoryId) { return productRepository.findByCategory_Id(categoryId); }

    @Override
    public List<Product> findByPriceRange(Double min, Double max) { return productRepository.findByPriceBetween(min, max); }
}