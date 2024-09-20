package com.example.demo2.product_supplier.mapper;
import com.example.demo2.product_supplier.entity.Product;
import com.example.demo2.product_supplier.request.ProductRequest;
import com.example.demo2.product_supplier.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "idPro", target = "id")
    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest productRequest);

    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}

