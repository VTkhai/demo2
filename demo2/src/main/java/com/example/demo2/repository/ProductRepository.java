package com.example.demo2.repository;

import com.example.demo2.entity.product.Product;
import com.example.demo2.model.resource.ProductResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String productName);
    List<Product> findByPriceGreaterThanEqual(Double price);
    List<Product> findByPriceLessThanEqual(Double price);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
