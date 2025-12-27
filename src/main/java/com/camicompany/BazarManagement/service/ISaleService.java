package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.dto.SaleDTO;
import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    //READ ALL
    public List<SaleDTO> getAllSales();
    //CREATE
    public SaleDTO createSale(SaleDTO saleDTO);
    //READ BY ID
    public SaleDTO getSaleById(Long id);
    //UPDATE
    public SaleDTO updateSale(Long id, SaleDTO saleDetailsDTO);
    //DELETE BY ID
    public void deleteSale(Long id);

    //PRODUCT LIST BY SALE ID
    public List<ProductDTO> getProductsBySaleId(Long saleId);

    //GET TOTAL SALES AMOUNT BY DATE AND TOTAL AMOUNT
    public SalesSummaryDTO getTotalSalesAmountByDateAndtotalAmount(LocalDate date);

    //GET SALE WITH GREATEST TOTAL AMOUNT
    public SaleDTO getSaleWithGreatestTotalAmount();

}
