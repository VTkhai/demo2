package com.example.demo2.product_supplier.service.impl;

import com.example.demo2.product_supplier.resource.SupplierResource;
import com.example.demo2.product_supplier.entity.Supplier;
import com.example.demo2.product_supplier.exception.ResourceNotFoundException;
import com.example.demo2.product_supplier.mapper.SupplierMapper;
import com.example.demo2.product_supplier.repository.SupplierRepository;
import com.example.demo2.product_supplier.request.SupplierRequest;
import com.example.demo2.product_supplier.response.SupplierResponse;
import com.example.demo2.product_supplier.service.SupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Service vs Controller ginog nhau ve chuc nang nhung khac nhau ve y nghia
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierResource createSupplier(SupplierRequest supplierRequest) {
        if (supplierRequest == null) {
            throw new RuntimeException("Cannot be null");
        }
        Supplier supplier = SupplierMapper.INSTANCE.toEntity(supplierRequest);

        // Lưu Supplier vào cơ sở dữ liệu
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Trả về phản hồi dưới dạng SupplierResponse
        SupplierResponse supplierResponse = SupplierMapper.INSTANCE.toResponse(savedSupplier);
        return new SupplierResource(supplierResponse);
    }
    @Override
    public SupplierResource updateSupplier(Long idSup, SupplierRequest supplierRequest) {
        Supplier existingSupplier = supplierRepository.findById(idSup)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idSup + " not found."));
        SupplierMapper.INSTANCE.updateSupplierFromRequest(supplierRequest, existingSupplier);

        // Lưu Supplier đã cập nhật vào cơ sở dữ liệu
        Supplier updatedSupplier = supplierRepository.save(existingSupplier);

        // Trả về phản hồi dưới dạng SupplierResponse
        SupplierResponse supplierResponse =  SupplierMapper.INSTANCE.toResponse(updatedSupplier);
        return new SupplierResource(supplierResponse);
    }

    @Override
    public void deleteSupplier(Long idSup) {
        Supplier existingSupplier = supplierRepository.findById(idSup)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idSup + " not found."));
        supplierRepository.delete(existingSupplier);
    }

    @Override
    public SupplierResource getSupplierDetails(Long idSup) {
        Supplier supplier = supplierRepository.findById(idSup)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idSup + " not found."));
        SupplierResponse supplierResponse =  SupplierMapper.INSTANCE.toResponse(supplier);
        return  new SupplierResource(supplierResponse);
    }

    @Override
    public List<SupplierResource> getAllSupplier() {
        List<Supplier> suppliers = supplierRepository.findAll();
         return suppliers.stream()
                .map(supplier -> {SupplierResponse supplierResponse
                        = SupplierMapper.INSTANCE.toResponse(supplier);
                return new SupplierResource(supplierResponse);
                })
                 .collect(Collectors.toList());
    }

    @Override
    public List<SupplierResource> searchSuppliers(String name) {
        List<Supplier> suppliers;

        //FIXME null vs empty
        if (StringUtils.isNotEmpty(name)) {
            suppliers = supplierRepository.findByNameContainingIgnoreCase(name);
        } else {
            suppliers = supplierRepository.findAll(); // Nếu không có tiêu chí nào, trả về tất cả
        }

        return suppliers.stream()
                .map(supplier-> {
                    SupplierResponse supplierResponse = SupplierMapper.INSTANCE.toResponse(supplier);
                    return new SupplierResource(supplierResponse);
                })
                .collect(Collectors.toList());
    }
}

