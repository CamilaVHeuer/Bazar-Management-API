package com.camicompany.BazarManagement.controller;


import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.model.Sale;
import com.camicompany.BazarManagement.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SaleController {
    @Autowired
    private ISaleService saleServ;

    @PostMapping("/sales/create")
    public Sale createSale(@RequestBody Sale sale) {

        return saleServ.createSale(sale);
    }

    @GetMapping("/sales")
    public List<Sale> getAllSales()
    {
        return saleServ.getAllSales();
    }

    @GetMapping ("/sales/{id}")
    public Sale getSaleById(@PathVariable Long id){

        return saleServ.getSaleById(id);
    }

    @PutMapping ("/sales/update/{id}")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale newSaleData) {
        return saleServ.updateSale(id, newSaleData);
    }

    @DeleteMapping ("/sales/delete/{id}")
    public void deleteSale(@PathVariable Long id) {
       saleServ.deleteSale(id);
    }

    @GetMapping ("/sales/products/{saleId}")
    public List<Product> getProductsBySaleId(@PathVariable Long saleId) {
        return saleServ.getProductsBySaleId(saleId);
    }

    @GetMapping ("/sales/date/{date}")
    public SalesSummaryDTO getTotalSalesAmountByDateAndtotalAmount(@PathVariable LocalDate date){
        return saleServ.getTotalSalesAmountByDateAndtotalAmount(date);
    }
    @GetMapping ("/sales/greatest-total-amount")
    public SalesDetailDTO getSaleWithGreatestTotalAmount(){
        return saleServ.getSaleWithGreatestTotalAmount();
    }

}
