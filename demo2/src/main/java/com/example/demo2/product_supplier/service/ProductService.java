package com.example.demo2.product_supplier.service;

import com.example.demo2.product_supplier.resource.ProductResource;
import com.example.demo2.product_supplier.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
//Service vs Controller giong nhau ve chuc nang nhung khac nhau ve y nghia
@Service
public interface ProductService {
    ProductResource updateProduct(Long idPro, ProductRequest productRe);
    void deleteProduct(Long idPro);
    ProductResource getProductDetails (Long idPro);
    List<ProductResource> getAllProducts();
    ProductResource createProduct(ProductRequest productRequest);
    List<ProductResource> searchProducts(String productName);
}
