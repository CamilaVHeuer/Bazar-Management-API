package com.camicompany.BazarManagement.repository;

import com.camicompany.BazarManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStockLessThanEqual(Integer stock);
}
