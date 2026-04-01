package org.example.lesson2.exercise1.controller;

import jakarta.validation.Valid;
import org.example.lesson2.exercise1.dto.ApiResponse;
import org.example.lesson2.exercise1.model.Product;
import org.example.lesson2.exercise1.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        List<Product> list = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>(200, "Lấy danh sách thành công", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Lấy sản phẩm thành công", product));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(new ApiResponse<>(201, "Tạo sản phẩm thành công", createdProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(new ApiResponse<>(200, "Cập nhật thành công", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Xóa sản phẩm thành công", null));
    }
}