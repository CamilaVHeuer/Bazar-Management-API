package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.dto.SaleDTO;
import com.camicompany.BazarManagement.dto.SaleDateDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.service.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "Operations for managing sales")
public class SaleController {
    @Autowired
    private ISaleService saleServ;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Sale created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data or insufficient stock")
    @Operation(summary = "Create a new sale", description = "Creates a new sale and returns the created sale DTO.")
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO createdSale = saleServ.createSale(saleDTO);
        return ResponseEntity.created(URI.create("/api/sales/" + createdSale.getSaleId())).body(createdSale);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of sales returned")
    @Operation(summary = "Get all sales", description = "Returns a list of all sales.")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        return ResponseEntity.ok(saleServ.getAllSales());
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Sale found")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @Operation(summary = "Get sale by ID", description = "Returns a sale DTO by its ID.")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleServ.getSaleById(id));
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Sale updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @Operation(summary = "Update sale", description = "Updates sale date and/or customer by ID and returns the updated DTO.")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDateDTO newSaleDateDTO) {
        return ResponseEntity.ok(saleServ.updateSale(id, newSaleDateDTO));
    }

    @PutMapping("/cancel/{id}")
    @ApiResponse(responseCode = "200", description = "Sale cancelled successfully")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @ApiResponse(responseCode = "409", description = "Sale already cancelled")
    @Operation(summary = "Cancel sale", description = "Cancels a sale, restores stock, and sets status to CANCELLED.")
    public ResponseEntity<SaleDTO> cancelSale(@PathVariable Long id) {
        return ResponseEntity.ok(saleServ.cancelSale(id));
    }

    @GetMapping("/products/{saleId}")
    @ApiResponse(responseCode = "200", description = "Products from sale returned")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @Operation(summary = "Get products from a sale", description = "Returns the list of products involved in a specific sale.")
    public ResponseEntity<List<ProductDTO>> getProductsBySaleId(@PathVariable Long saleId) {
        return ResponseEntity.ok(saleServ.getProductsBySaleId(saleId));
    }

    @GetMapping("/date/{date}")
    @ApiResponse(responseCode = "200", description = "Sales summary returned")
    @ApiResponse(responseCode = "400", description = "Invalid date format")
    @Operation(summary = "Get sales summary by date", description = "Returns the total sales amount and summary for a specific date.")
    public ResponseEntity<SalesSummaryDTO> getTotalSalesAmountByDateAndtotalAmount(@PathVariable LocalDate date) {
        return ResponseEntity.ok(saleServ.getTotalSalesAmountByDateAndtotalAmount(date));
    }

    @GetMapping("/greatest-total-amount")
    @ApiResponse(responseCode = "200", description = "Sale with greatest total amount returned")
    @ApiResponse(responseCode = "404", description = "No sales found")
    @Operation(summary = "Get sale with greatest total amount", description = "Returns the sale with the highest total amount.")
    public ResponseEntity<SaleDTO> getSaleWithGreatestTotalAmount() {
        return ResponseEntity.ok(saleServ.getSaleWithGreatestTotalAmount());
    }

}
