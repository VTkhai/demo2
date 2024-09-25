package com.example.demo2.controller.product;
import com.example.demo2.entity.product.Product;
import com.example.demo2.model.resource.ProductResource;
import com.example.demo2.model.request.product.ProductRequest;
import com.example.demo2.service.product.ProductService;
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
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Get ProductDetails base on idPro",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, "
                    + "description and published status")
    @GetMapping("/{idPro}")
    public ResponseEntity<ProductResource> getProduct(@PathVariable(name = "idPro") Long id) {
        ProductResource productResource = productService.getProductDetails(id);
        return ResponseEntity.ok(productResource);
    }

    @Operation(summary = "Get ProductList",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, "
                    + "description and published status")
    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts () {
        List<ProductResource> productResources = productService.getAllProducts();
        return ResponseEntity.ok(productResources);
    }


    @Operation(summary = "Create product", description = "Create new Product")
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResource productResource = productService.createProduct(productRequest);
        return ResponseEntity.ok(productResource);
    }

    //TODO phan biet Put vs Patch
    @Operation(summary = "Update product base on info product need to update",
                description = "Get a Product object by specifying its id. The response is " +
                        "Product object with id, title," + " description and published status")
    @PutMapping("/{idPro}")
    public ResponseEntity<ProductResource> updateProduct(
            @Valid
            @PathVariable (name ="idPro") Long idPro,
            @RequestBody ProductRequest productRequest
    ){
        ProductResource productResource = productService.updateProduct(idPro, productRequest);
        return ResponseEntity.ok(productResource);
    }

    @Operation(summary = "Search product base on name", description = "Search Product")
    @GetMapping("/search")
    public ResponseEntity<List<ProductResource>> searchProducts(
             @RequestParam(required = false) String productName)
    {
        List<ProductResource> productResources = productService.searchProducts(productName);
        return ResponseEntity.ok(productResources);
    }

    @Operation(summary = "Delete product base on idPro",
                description = "Delete product from data")
    @DeleteMapping("/{idPro}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idPro) {
        productService.deleteProduct(idPro);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/search/price/greater-than/{price}")
    public ResponseEntity<List<ProductResource>> findByPriceGreaterThan(@PathVariable Double price) {
        List<ProductResource> products = productService.findProductsByPriceGreaterThanEqual(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/price/less-than/{price}")
    public ResponseEntity<List<ProductResource>> findByPriceLessThan(@PathVariable Double price) {
        List<ProductResource> products = productService.findProductsByPriceLessThanEqual(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/price/between")
    public ResponseEntity<List<ProductResource>> findByPriceBetween(@RequestParam Double minPrice,
                                                            @RequestParam Double maxPrice) {
        List<ProductResource> products = productService.findProductsByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}
