package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.model.Product;

import java.util.List;

public interface IProductService {

    //READ ALL
    public List<Product> getAllProducts();

    //CREATE
    public Product createProduct(Product product);

    //READ BY ID
    public Product getProductById(Long id);

    //UPDATE
    public Product updateProduct(Long id, Product productDetails);

    //DELETE
    public void deleteProduct(Long id);

    //GET Products with low stock
    public List<Product> getProductsLowStock();


}
