package com.example.demo2.product_supplier.controller;

import com.example.demo2.product_supplier.resource.SupplierResource;
import com.example.demo2.product_supplier.request.SupplierRequest;
import com.example.demo2.product_supplier.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Service vs Controller giong nhau ve chuc nang nhung khac nhau ve y nghia
@RestController
@CrossOrigin
@RequestMapping("/suppliers")
@Tag(name = "Suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Operation(summary = "Get SupplierDetails base on idSup",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name,"
                    + " address")
    @GetMapping("/{idSup}")
    public  ResponseEntity<SupplierResource> getSupplierDetails(
            @PathVariable(name = "idSup") Long idSup
    ){
        SupplierResource supplierResource = supplierService.getSupplierDetails(idSup);
        return ResponseEntity.ok(supplierResource);
    }

    @Operation(summary = "Get SupplierDetails base on idSup",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name, "
                    + "address")
    @GetMapping
    public ResponseEntity<List<SupplierResource>> getAllSuppliers(){
        List<SupplierResource> supplierResources = supplierService.getAllSupplier();
        return ResponseEntity.ok(supplierResources);
    }

    @Operation(summary = "Create Supplier base on info",
            description = "Create a Supplier object")
    @PostMapping
    public ResponseEntity<SupplierResource> createSupplier(
            @Valid
            @RequestBody SupplierRequest supplierRequest) {
        SupplierResource supplierResource = supplierService.createSupplier(supplierRequest);
        return ResponseEntity.ok(supplierResource);
    }

    @Operation(summary = "Update Supplier base on info",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name,"
                    + " address")
    @PutMapping("/{idSup}")
    public ResponseEntity<SupplierResource> updateSupplier(
            @Valid
            @PathVariable(name = "idSup") Long idSup,
            @RequestBody SupplierRequest supplierRequest
    ){
        SupplierResource supplierResource = supplierService.updateSupplier(idSup, supplierRequest);
        return ResponseEntity.ok(supplierResource);
    }

    @Operation(summary = "Delete Supplier base on idSup",
            description = "Delete a Supplier object by specifying its id")
    @DeleteMapping("/{idSup}")
    public ResponseEntity<Void> deleteSupplier(
            @PathVariable Long idSup
    ){
        supplierService.deleteSupplier(idSup);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Search suppliers base on name", description = "Search Supplier")
    @GetMapping("/search")
    public ResponseEntity<List<SupplierResource>> searchSuppliers(
            @RequestParam(required = false) String name) {

        List<SupplierResource> supplierResources = supplierService.searchSuppliers(name);
        return ResponseEntity.ok(supplierResources);
    }

}
