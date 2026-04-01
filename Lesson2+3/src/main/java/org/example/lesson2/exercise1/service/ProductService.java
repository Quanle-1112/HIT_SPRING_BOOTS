package org.example.lesson2.exercise1.service;

import org.example.lesson2.exercise1.exception.DuplicateResourceException;
import org.example.lesson2.exercise1.exception.ResourceNotFoundException;
import org.example.lesson2.exercise1.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private long currentId = 1;

    public Product createProduct(Product product) {
        boolean isDuplicate = products.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()));

        if (isDuplicate) {
            throw new DuplicateResourceException("Sản phẩm với tên '" + product.getName() + "' đã tồn tại!");
        }

        product.setId(currentId++);
        products.add(product);
        return product;
    }


    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm có ID: " + id));
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = getProductById(id);

        boolean isDuplicate = products.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(productDetails.getName()) && !p.getId().equals(id));
        if (isDuplicate) {
            throw new DuplicateResourceException("Tên sản phẩm '" + productDetails.getName() + "' đã bị trùng!");
        }

        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setCategory(productDetails.getCategory());

        return existingProduct;
    }

    public void deleteProduct(Long id) {
        Product existingProduct = getProductById(id);
        products.remove(existingProduct);
    }
}