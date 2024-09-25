package com.example.demo2.mapper;
import com.example.demo2.entity.supplier.Supplier;
import com.example.demo2.model.request.supplier.SupplierRequest;
import com.example.demo2.model.response.SupplierResponse;
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
