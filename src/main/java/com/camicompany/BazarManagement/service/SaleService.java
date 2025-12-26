package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.model.Customer;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.model.Sale;
import com.camicompany.BazarManagement.model.SalesDetail;
import com.camicompany.BazarManagement.repository.ICustomerRepository;
import com.camicompany.BazarManagement.repository.IProductRepository;
import com.camicompany.BazarManagement.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private ISaleRepository saleRepo;

    @Autowired
    private ICustomerRepository custoRepo;

    @Autowired
    private IProductRepository prodRepo;

    @Override
    public List<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    @Override
    public Sale createSale(Sale sale) {
        //Find the real customer in the database
        Customer custo = custoRepo.findById(sale.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        //Create a new "cleaned" sale
        Sale newSale = new Sale();
        newSale.setDateSale(sale.getDateSale());
        newSale.setCustomer(custo);

        double total = 0.0;
        List<SalesDetail> cleanItems = new ArrayList<>();
        //Process each item
        for (SalesDetail item : sale.getItems()) {
            //Find the real product in the database
            Product prod = prodRepo.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + item.getProduct().getProductId()));
            // Validate stock
            if (prod.getStock() < item.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + prod.getName());
            }
            //Create a new "cleaned" items
            SalesDetail cleanItem = new SalesDetail();
            cleanItem.setProduct(prod);
            cleanItem.setQuantity(item.getQuantity());
            cleanItem.setUnitPrice(prod.getUnitPrice());

            //Calculate subtotal
            double subTotal = prod.getUnitPrice() * item.getQuantity();
            cleanItem.setSubTotal(subTotal);
            // Set the sale reference
            cleanItem.setSale(newSale);
            total += subTotal;

            cleanItems.add(cleanItem);
            //Update product stock
            prod.setStock(prod.getStock() - item.getQuantity());
            prodRepo.save(prod);
         }

        // Link item to sale
        newSale.setItems(cleanItems);
        newSale.setTotal(total);

        return saleRepo.save(newSale); // Save the sale to generate the saleId
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepo.findById(id).orElse(null);
    }

    @Override
    public Sale updateSale(Long id, Sale newSaleData) {
        Sale existingSale = saleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
        //update only fields that can be updated
        if(newSaleData.getCustomer()!=null){
            Customer custo = custoRepo.findById(newSaleData.getCustomer().getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            existingSale.setCustomer(custo);
        }

        if(newSaleData.getDateSale()!=null){
            existingSale.setDateSale(newSaleData.getDateSale());
        }
        return saleRepo.save(existingSale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
        saleRepo.delete(sale);

    }

    @Override
    public List<Product> getProductsBySaleId(Long saleId) {
        //Find the sale
        Sale sale = saleRepo.findById(saleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
        List<Product> products = new ArrayList<>();
        //Extract products from sale items
        for(SalesDetail item : sale.getItems()){
            products.add(item.getProduct());
        }
        return products;
    }

    @Override
    public SalesSummaryDTO getTotalSalesAmountByDateAndtotalAmount(LocalDate date) {
        //Fetch sales by date
        List<Sale> sales = saleRepo.findByDateSale(date);
        Double totalAmount = 0.0;
        //Sum totals
        for(Sale sale : sales){
            totalAmount += sale.getTotal();}
        Integer totalSales = sales.size();

        return new SalesSummaryDTO(totalAmount, totalSales);
    }

    @Override
    public SalesDetailDTO getSaleWithGreatestTotalAmount() {
        //Fetch all sales
        List<Sale> sales = saleRepo.findAll();

        if(sales.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sales found");
        }

        //Find the sale with the greatest total
        Sale maxSale = sales.get(0);

        for (Sale sale : sales) {
            if (sale.getTotal() > maxSale.getTotal()) {
            maxSale = sale;
        }
        }
        // Calculate total products (sum of quantities)
        int totalProducts = 0;
        for (SalesDetail item : maxSale.getItems()) {
            totalProducts += item.getQuantity();
        }
        // Build DTO
        SalesDetailDTO dto = new SalesDetailDTO();
        dto.setSaleId(maxSale.getSaleId());
        dto.setTotalSaleAmount(maxSale.getTotal());
        dto.setCustomerName(maxSale.getCustomer().getFisrtName());
        dto.setCustomerLastName(maxSale.getCustomer().getLastName());
        dto.setTotalProd(totalProducts);

        return dto;

    }

}
