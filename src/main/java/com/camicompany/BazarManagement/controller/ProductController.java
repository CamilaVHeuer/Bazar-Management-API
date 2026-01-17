package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Operations for managing products")
public class ProductController {

    @Autowired
    private IProductService prodServ;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @Operation(summary = "Create a new product", description = "Creates a new product and returns the created product DTO.")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO prodDTO) {
        ProductDTO createdProduct = prodServ.createProduct(prodDTO);
        return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getProductId()))
                .body(createdProduct);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of products returned")
    @Operation(summary = "Get all products", description = "Returns a list of all products.")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(prodServ.getAllProducts());
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @Operation(summary = "Get product by ID", description = "Returns a product DTO by its ID.")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(prodServ.getProductById(id));
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "409", description = "Product is discontinued and cannot be updated")
    @Operation(summary = "Update product", description = "Updates product data by ID and returns the updated DTO.")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO prodDetailsDTO) {
        return ResponseEntity.ok(prodServ.updateProduct(id, prodDetailsDTO));
    }

    @PutMapping("discontinue/{id}")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "409", description = "Product is already discontinued")
    @Operation(summary = "Discontinue product", description = "Discontinues a product by ID.")
    public ResponseEntity<ProductDTO> discontinueProduct(@PathVariable Long id) {
       return ResponseEntity.ok().body(prodServ.discontinueProduct(id));

    }

    @PutMapping("activate/{id}")
    @ApiResponse(responseCode = "200", description = "Product activated successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "409", description = "Product is already active")
    @Operation(summary = "Activate product", description = "Activates a discontinued product by ID.")
    public ResponseEntity<ProductDTO> activateProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(prodServ.activateProduct(id));
    }

    @GetMapping("/low-stock")
    @ApiResponse(responseCode = "200", description = "Low stock products returned")
    @Operation(summary = "Get products with low stock", description = "Returns a list of products with stock less than or equal to 5.")
    public ResponseEntity<List<ProductDTO>> getProductsLowStock() {
        return ResponseEntity.ok(prodServ.getProductsLowStock());
    }

}
