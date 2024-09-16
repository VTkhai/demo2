package com.example.demo2.impl;
import com.example.demo2.Mapper.SupplierMapper;
import com.example.demo2.entity.Supplier;
import com.example.demo2.entity.SupplierM;
import com.example.demo2.repository.SupplierRepository;
import com.example.demo2.service.SupplierService;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierMapper supplierMapper = SupplierMapper.INSTANCE;
    @Autowired
    private  SupplierRepository supplierRepository;

    @Override
    public Long createSupplier(Supplier supplier) {
        Supplier supplierSaved = supplierRepository.save(supplier);
        return supplierSaved.getIdSup();
    }

    @Override
    public Long updateSupplier(Long idSup,Supplier supplier) {
        Supplier sup = supplierRepository.findById(idSup).orElse(null);
        sup.getName();
        sup.getAddress();
        supplierRepository.save(sup);
        return null;
    }



    @Override
    public String deleteSupplier(Long idSup) {
        supplierRepository.deleteById(idSup);
        return null;
    }


    @Override
    public SupplierM getSupplierDetails(Long idSup) {
        // logic here
        Supplier supplier = supplierRepository.findById(idSup).orElseThrow(()-> new RuntimeException("Product not found"));
        return supplierMapper.supplierToSupplierM(supplier);

    }

    @Override
    public List<Supplier> getAllSupplier() {
        return Streams.of(supplierRepository.findAll()).toList();
    }
}

