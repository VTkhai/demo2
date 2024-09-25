package com.example.demo2.service.product.impl;

import com.example.demo2.model.resource.ProductResource;
import com.example.demo2.entity.product.Product;
import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.mapper.ProductMapper;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.model.request.product.ProductRequest;
import com.example.demo2.model.response.ProductResponse;
import com.example.demo2.service.product.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
//Service vs Controller ginog nhau ve chuc nang nhung khac nhau ve y nghia
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResource createProduct(ProductRequest productRequest) {
        if (productRequest == null) {
            throw new RuntimeException("Cannot be null");
        }
        Product product = ProductMapper.INSTANCE.toEntity(productRequest);

        // Lưu sản phẩm vào cơ sở dữ liệu
        Product savedProduct = productRepository.save(product);

        // Trả về phản hồi dưới dạng ProductResponse
        ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(savedProduct);
        return new ProductResource(productResponse);

    }


    @Override
    public ProductResource updateProduct(Long idPro, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(idPro)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + idPro + " not found."));
        // Cập nhật thông tin sản phẩm từ productRequest
        ProductMapper.INSTANCE.updateProductFromRequest(productRequest, existingProduct);

        // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
        Product updatedProduct = productRepository.save(existingProduct);

        ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(updatedProduct);
        return new ProductResource(productResponse);
    }


    @Override
    public void deleteProduct(Long idPro) {
        Product existingProduct = productRepository.findById(idPro)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + idPro + " not found."));
        productRepository.delete(existingProduct);
    }


    @Override
    public ProductResource getProductDetails(Long idPro) {
        Product product = productRepository.findById(idPro)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + idPro + " not found."));
        ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(product);
        return new ProductResource(productResponse);
    }

    @Override
    public List<ProductResource> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse =
                            ProductMapper.INSTANCE.toResponse(product);
                    return new ProductResource(productResponse);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResource> searchProducts(String productName) {
        List<Product> products;

        //FIXME null vs empty
        if (StringUtils.isNotEmpty(productName)) {
            products = productRepository.findByProductNameContainingIgnoreCase(productName);
        } else {
            products = productRepository.findAll(); // Nếu không có tiêu chí nào, trả về tất cả
        }

        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(product);
                    return new ProductResource(productResponse);
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductResource> findProductsByPriceGreaterThanEqual(Double price) {
        List<Product> products;

        //FIXME null vs empty
        if (price == null) {
            products =  Collections.emptyList();
        } else {
            products =  productRepository.findByPriceGreaterThanEqual(price);
        }
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(product);
                    return new ProductResource(productResponse);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResource> findProductsByPriceLessThanEqual(Double price) {
        List<Product> products;

        //FIXME null vs empty
        if (price == null) {
            products =  Collections.emptyList();
        } else {
            products =  productRepository.findByPriceLessThanEqual(price);
        }
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(product);
                    return new ProductResource(productResponse);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResource> findProductsByPriceBetween(Double minPrice, Double maxPrice) {
        List<Product> products;

        //FIXME null vs empty
        if (minPrice == null && maxPrice == null) {
            products =  Collections.emptyList();
        } else {
            products =  productRepository.findByPriceBetween(
                    minPrice != null ? minPrice : 0.0,
                    maxPrice != null ? maxPrice : Double.MAX_VALUE );
        }
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = ProductMapper.INSTANCE.toResponse(product);
                    return new ProductResource(productResponse);
                })
                .collect(Collectors.toList());
    }

}
