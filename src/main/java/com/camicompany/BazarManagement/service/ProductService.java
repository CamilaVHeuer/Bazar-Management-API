package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll(); // Fetch all products from the repository
    }

    @Override
    public Product createProduct(Product product) {
       return productRepo.save(product); // Save the new product to the repository

    }

    @Override
    public Product getProductById(Long id) {
       return productRepo.findById(id).orElse(null); // Fetch product by ID or return null if not found

    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product not found"
                        ));

        product.setName(productDetails.getName());
        product.setUnitPrice(productDetails.getUnitPrice());
        product.setBrand(productDetails.getBrand());

        return productRepo.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found"
        ));
        productRepo.delete(product);

    }

    @Override
    public List<Product> getProductsLowStock() {
        return productRepo.findByStockLessThanEqual(5);
    }
}
