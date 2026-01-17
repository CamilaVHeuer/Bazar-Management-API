package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.model.Product;

import java.util.List;

public interface IProductService {

    //READ ALL
    public List<ProductDTO> getAllProducts();

    //CREATE
    public ProductDTO createProduct(ProductDTO productDTO);

    //READ BY ID
    public ProductDTO getProductById(Long id);

    //UPDATE
    public ProductDTO updateProduct(Long id, ProductDTO productDetailsDTO);

    //DISCONTINUE PRODUCT
    public ProductDTO discontinueProduct(Long id);

    //ACTIVATE PRODUCT
    public ProductDTO activateProduct(Long id);

    //GET Products with low stock
    public List<ProductDTO> getProductsLowStock();


}
