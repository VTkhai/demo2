package com.example.demo2.Mapper;
import com.example.demo2.entity.Supplier;
import com.example.demo2.entity.SupplierM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierMapper {
   SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    SupplierM supplierToSupplierM(Supplier supplier);

    Supplier supplierMToSupplier(SupplierM supplierM);
}
