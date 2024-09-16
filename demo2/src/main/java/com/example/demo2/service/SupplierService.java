package com.example.demo2.service;
import com.example.demo2.entity.Supplier;
import com.example.demo2.entity.SupplierM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
     Long createSupplier(Supplier supplier);
     Long updateSupplier(Long idSup, Supplier supplier);
     String deleteSupplier(Long idSup);
     SupplierM getSupplierDetails (Long idSup);
     List<Supplier> getAllSupplier();
}
