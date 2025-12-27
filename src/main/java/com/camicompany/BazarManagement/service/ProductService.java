package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.mapper.Mapper;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream().map(Mapper::toProductDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if(productDTO.getName() == null || productDTO.getName().trim().isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product name is required");
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setStock(productDTO.getStock());
        return Mapper.toProductDTO(productRepo.save(product));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found"));
        return Mapper.toProductDTO(product);

    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDetailsDTO) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"));

        if (productDetailsDTO.getName() != null && !productDetailsDTO.getName().trim().isEmpty()) {
            product.setName(productDetailsDTO.getName());
        }
        if (productDetailsDTO.getBrand() != null && !productDetailsDTO.getBrand().trim().isEmpty()) {
            product.setBrand(productDetailsDTO.getBrand());
        }
        if (productDetailsDTO.getUnitPrice() != null) {
            product.setUnitPrice(productDetailsDTO.getUnitPrice());
        }
        if (productDetailsDTO.getStock() != null) {
            product.setStock(productDetailsDTO.getStock());
        }

        return Mapper.toProductDTO(productRepo.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepo.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found");
        }
        productRepo.deleteById(id);

    }

    @Override
    public List<ProductDTO> getProductsLowStock() {
        return productRepo.findByStockLessThanEqual(5).stream().map(Mapper::toProductDTO).toList();
    }
}
