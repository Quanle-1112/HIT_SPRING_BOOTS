package org.example.btvn4.repository;

import org.example.btvn4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String keyword);
    List<Product> findByCategory_Id(Long categoryId);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByActiveTrue();
    boolean existsByNameAndCategory_Id(String name, Long categoryId);
}