package com.example.demo2.service;

import com.example.demo2.entity.Product;
import com.example.demo2.entity.ProductM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductM createProduct(ProductM productM);
    Long updateProduct(Long idPro, Product product);
    ProductM updateProduct(Long idPro, ProductM productM);
    void deleteProduct(Long idPro);
    ProductM getProductDetails (Long idPro);
    List<ProductM> getAllProducts();
}
