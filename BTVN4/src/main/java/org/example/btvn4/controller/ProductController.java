package org.example.btvn4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.btvn4.dto.ApiResponse;
import org.example.btvn4.dto.CreateProductRequest;
import org.example.btvn4.entity.Product;
import org.example.btvn4.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(productService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable Long id, @Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> searchByName(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(productService.searchByName(keyword)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<Product>>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(productService.findByCategoryId(categoryId)));
    }

    @GetMapping("/price")
    public ResponseEntity<ApiResponse<List<Product>>> getByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(ApiResponse.success(productService.findByPriceRange(min, max)));
    }
}