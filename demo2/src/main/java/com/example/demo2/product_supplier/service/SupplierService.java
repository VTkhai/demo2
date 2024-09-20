package com.example.demo2.product_supplier.service;
import com.example.demo2.product_supplier.resource.SupplierResource;
import com.example.demo2.product_supplier.request.SupplierRequest;
import org.springframework.stereotype.Service;

import java.util.List;
//Service vs Controller giong nhau ve chuc nang nhung khac nhau ve y nghia
@Service
public interface SupplierService {
     SupplierResource createSupplier(SupplierRequest supplierRequest);
     SupplierResource updateSupplier(Long idSup, SupplierRequest supplierRequest);
     void deleteSupplier(Long idSup);
     SupplierResource getSupplierDetails (Long idSup);
     List<SupplierResource> getAllSupplier();
     List<SupplierResource> searchSuppliers(String supplierName);
}
