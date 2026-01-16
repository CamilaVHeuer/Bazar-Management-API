package com.camicompany.BazarManagement.controller;


import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.dto.SaleDTO;
import com.camicompany.BazarManagement.dto.SaleDateDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/api/sales")
public class SaleController {
    @Autowired
    private ISaleService saleServ;

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
    SaleDTO createdSale = saleServ.createSale(saleDTO);
        return ResponseEntity.created(URI.create("/api/sales" + createdSale.getSaleId())).body(createdSale);
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        return ResponseEntity.ok(saleServ.getAllSales());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id){
        return ResponseEntity.ok(saleServ.getSaleById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDateDTO newSaleDateDTO) {
        return ResponseEntity.ok(saleServ.updateSale(id, newSaleDateDTO));
    }

    @PutMapping ("/cancel/{id}")
    public ResponseEntity<SaleDTO> cancelSale(@PathVariable Long id) {
       return ResponseEntity.ok(saleServ.cancelSale(id));
    }

    @GetMapping ("/products/{saleId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySaleId(@PathVariable Long saleId) {
        return ResponseEntity.ok(saleServ.getProductsBySaleId(saleId));
    }

    @GetMapping ("/date/{date}")
    public ResponseEntity<SalesSummaryDTO> getTotalSalesAmountByDateAndtotalAmount(@PathVariable LocalDate date){
        return ResponseEntity.ok(saleServ.getTotalSalesAmountByDateAndtotalAmount(date));
    }
    @GetMapping ("/greatest-total-amount")
    public ResponseEntity<SaleDTO> getSaleWithGreatestTotalAmount(){
        return ResponseEntity.ok(saleServ.getSaleWithGreatestTotalAmount());
    }

}
