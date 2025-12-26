package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService prodServ;

    @PostMapping ("/products/create")
    public Product createProduct(@RequestBody Product prod) {
       return prodServ.createProduct(prod);
    }

    @GetMapping ("/products")
    public List<Product> getAllProducts() {
        return prodServ.getAllProducts();
    }

    @GetMapping ("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return prodServ.getProductById(id);
    }

    @PutMapping ("/products/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product prodDetails) {
        return prodServ.updateProduct(id, prodDetails);
    }

    @DeleteMapping ("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        prodServ.deleteProduct(id);
    }

    @GetMapping ("/products/low-stock")
    public List<Product> getProductsLowStock() {
        return prodServ.getProductsLowStock();
    }


}
