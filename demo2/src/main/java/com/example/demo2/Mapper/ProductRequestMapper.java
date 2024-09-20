//package com.example.demo2.mapper;
//
//import com.example.demo2.entity.Product;
//import com.example.demo2.response.ProductResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//
//@Service
//public class ProductRequestMapper implements Function<Product, ProductResponse> {
//    @Override
//    public  ProductResponse apply(Product product){
//        return new ProductResponse(
//                product.getIdPro(),
//                product.getProductName(),
//                product.getDescription(),
//                product.getPrice()
//        );
//    }
//}
