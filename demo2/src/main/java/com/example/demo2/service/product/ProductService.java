package com.example.demo2.service.product;

import com.example.demo2.model.resource.ProductResource;
import com.example.demo2.model.request.product.ProductRequest;
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
    List<ProductResource> findProductsByPriceGreaterThanEqual(Double price);
    List<ProductResource> findProductsByPriceLessThanEqual(Double price);
    List<ProductResource> findProductsByPriceBetween(Double minPrice, Double maxPrice);
}
