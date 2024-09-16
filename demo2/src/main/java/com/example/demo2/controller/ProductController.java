package com.example.demo2.controller;
import com.example.demo2.entity.ProductM;
import com.example.demo2.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo2.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Get ProductDetails base on idPro",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, " +
                    "description and published status")
    @GetMapping("/{idPro}")
    public ResponseEntity<ProductM> getProductDetails(@PathVariable(name = "idPro") Long idPro) {
        ProductM productM = productService.getProductDetails(idPro);
        return new ResponseEntity<>(productM, HttpStatus.OK);

    }


    @Operation(summary = "Get ProductDetails base on idPro",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, " +
                    "description and published status")
    @GetMapping
    public ResponseEntity<List<ProductM>> getAllProducts() {
        List<ProductM> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @Operation(summary = "Create product", description = "Create new Product")
    @PostMapping
    public ResponseEntity<ProductM> createProduct(@Valid @RequestBody ProductM productM){
       ProductM createdProduct = productService.createProduct(productM);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }




    @Operation(summary = "Update product base on info product need to update",
                description = "Get a Product object by specifying its id. The response is Product object with id, " +
                        "title," + " description and published status")
    @PutMapping("/{idPro}")
    public ResponseEntity<ProductM> updateProduct(@PathVariable (name ="idPro") Long idPro,@Valid @RequestBody ProductM productM){
        ProductM updatedProduct = productService.updateProduct(idPro, productM);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }




    @Operation(summary = "Delete product base on idPro",
                description = "Delete product from data")
    @DeleteMapping("/{idPro}")
    public ResponseEntity<Void> deleteProduct(@PathVariable (name ="idPro") Long idPro){
             productService.deleteProduct(idPro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
