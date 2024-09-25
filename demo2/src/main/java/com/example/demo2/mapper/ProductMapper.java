package com.example.demo2.mapper;
import com.example.demo2.entity.product.Product;
import com.example.demo2.model.request.product.ProductRequest;
import com.example.demo2.model.response.ProductResponse;
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

