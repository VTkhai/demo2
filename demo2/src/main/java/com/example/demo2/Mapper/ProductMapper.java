package com.example.demo2.Mapper;
import com.example.demo2.entity.Product;
import com.example.demo2.entity.ProductM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductM productToProductM(Product product);

    Product productMToProduct(ProductM productM);
}
