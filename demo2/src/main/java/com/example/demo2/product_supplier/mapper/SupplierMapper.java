package com.example.demo2.product_supplier.mapper;
import com.example.demo2.product_supplier.entity.Supplier;
import com.example.demo2.product_supplier.request.SupplierRequest;
import com.example.demo2.product_supplier.response.SupplierResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    @Mapping(source = "idSup", target = "idSup")
    SupplierResponse toResponse(Supplier supplier);

   Supplier toEntity(SupplierRequest supplierRequest);

    void updateSupplierFromRequest(SupplierRequest supplierRequest, @MappingTarget Supplier supplier);
}
