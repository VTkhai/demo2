package com.example.demo2.impl;
import com.example.demo2.Mapper.ProductMapper;
import com.example.demo2.entity.Product;
import com.example.demo2.entity.ProductM;
import com.example.demo2.exception.BusinessException;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    @Autowired
    private  ProductRepository productRepository;


    @Override
    public ProductM createProduct(ProductM productM) {
        Product product = productMapper.productMToProduct(productM);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductM(savedProduct);
    }

    @Override
    public ProductM updateProduct(Long idPro, ProductM productM) {
        if (!productRepository.existsById(idPro)) {
            throw new BusinessException("Product not found with id: " + idPro);
        }
        Product product = productMapper.productMToProduct(productM);
        product.setIdPro(idPro); // Ensure the ID is set for update
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductM(updatedProduct);
    }
//    public Long updateProduct(Long idPro, Product product) {
//        Product pro = productRepository.findById(idPro).orElse(null);
//        pro.setProductName(product.getProductName());
//        pro.getPrice();
//        pro.getDescription();
//        productRepository.save(pro);
//        return null;
//    }


    @Override
    public void deleteProduct(Long idPro) {
        if (!productRepository.existsById(idPro)) {
            throw new BusinessException("Product not found with id: " + idPro);
        }
        productRepository.deleteById(idPro);
    }

    @Override
    public ProductM getProductDetails(Long idPro) {
        // logic here
Product product = productRepository.findById(idPro).orElseThrow(()-> new RuntimeException("Product not found"));
        return productMapper.productToProductM(product);
    }

    @Override
    public List<ProductM> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductM)
                .toList();
    }

}
