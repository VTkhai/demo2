package com.example.demo2.controller;

import com.example.demo2.entity.Product;
import com.example.demo2.entity.Supplier;
import com.example.demo2.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@Tag(name = "Suppliers")
public class SupplierController {

    @Autowired
     SupplierService supplierService;

    @Operation(summary = "Get SupplierDetails base on idSup",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name," +
                    " address")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "404", description = "The Supplier with given Id was not found.")
    })
    @GetMapping("/{idSup}")
    public  Supplier getSupplierDetails(@PathVariable(name = "idSup") Long idSup)
    {
        return supplierService.getSupplierDetails(idSup);
    }


    @Operation(summary = "Get SupplierDetails base on idSup",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name, "
                    + "address")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "404", description = "The Supplier with given Id was not found.")
    })
    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSupplier();
    }

    @Operation(summary = "Create Supplier base on info",
            description = "Create a Supplier object")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "404", description = "Failed to create")
    })
    @PostMapping
    public String createSupplier(@RequestBody Supplier supplier){
        supplierService.createSupplier(supplier);
        return "Supplier create Success";
    }



    @Operation(summary = "Update Supplier base on info",
            description = "Get a Supplier object by specifying its id. The response is Supplier object with id, name," +
                    " address")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "404", description = "Failed to update")
    })
    @PutMapping("/{idSup}")
    public String updateSupplier(@PathVariable(name = "idSup")Long idSup,@RequestBody Supplier supplier){
        supplierService.updateSupplier(idSup ,supplier);
        return "Supplier Update Success";
    }




    @Operation(summary = "Delete Supplier base on idSup",
            description = "Delete a Supplier object by specifying its id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Supplier.class))),
            @ApiResponse(responseCode = "404", description = "The Supplier with given Id was not found.")
    })
    @DeleteMapping("/{idSup}")
    public String deleteSupplier(@PathVariable(name = "idSup") Long idSup){
        supplierService.deleteSupplier(idSup);
        return "Supplier delete Success";
    }
}
