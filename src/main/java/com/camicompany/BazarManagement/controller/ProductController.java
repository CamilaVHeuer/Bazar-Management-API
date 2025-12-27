package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/products")
public class ProductController {

    @Autowired
    private IProductService prodServ;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO prodDTO) {
       ProductDTO createdProduct = prodServ.createProduct(prodDTO);
        return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getProductId())).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(prodServ.getAllProducts());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){

        return ResponseEntity.ok(prodServ.getProductById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO prodDetailsDTO) {
        return ResponseEntity.ok(prodServ.updateProduct(id, prodDetailsDTO));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        prodServ.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/low-stock")
    public ResponseEntity<List<ProductDTO>> getProductsLowStock() {
        return ResponseEntity.ok(prodServ.getProductsLowStock());
    }


}
