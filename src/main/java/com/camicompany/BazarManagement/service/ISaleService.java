package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    //READ ALL
    public List<Sale> getAllSales();
    //CREATE
    public Sale createSale(Sale sale);
    //READ BY ID
    public Sale getSaleById(Long id);
    //UPDATE
    public Sale updateSale(Long id, Sale saleDetails);
    //DELETE
    public void deleteSale(Long id);

    //PRODUCT LIST BY SALE ID
    public List<Product> getProductsBySaleId(Long saleId);

    //GET TOTAL SALES AMOUNT BY DATE AND TOTAL AMOUNT
    public SalesSummaryDTO getTotalSalesAmountByDateAndtotalAmount(LocalDate date);

    //GET SALE WITH GREATEST TOTAL AMOUNT
    public SalesDetailDTO getSaleWithGreatestTotalAmount();

}
