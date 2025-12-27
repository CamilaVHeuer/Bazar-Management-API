package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.dto.SaleDTO;
import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.dto.SalesSummaryDTO;
import com.camicompany.BazarManagement.mapper.Mapper;
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
import java.util.Comparator;
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
    public List<SaleDTO> getAllSales() {
        return saleRepo.findAll().stream().map(Mapper::toSaleDTO).toList();
    }

    @Override
    public SaleDTO createSale(SaleDTO saleDTO) {
        if(saleDTO ==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale data is null");
        }
        if(saleDTO.getItems() == null || saleDTO.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale must have at least one item");
        }
        if(saleDTO.getCustomerId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer ID is required");
        }
        //Find the real customer in the database
        Customer custo = custoRepo.findById(saleDTO.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        //Create a new "cleaned" sale
        Sale newSale = new Sale();
        newSale.setDateSale(saleDTO.getDateSale());
        newSale.setCustomer(custo);

        double total = 0.0;
        List<SalesDetail> cleanItems = new ArrayList<>();
        //Process each item
        for (SalesDetailDTO item : saleDTO.getItems()) {
            //Find the real product in the database
            Product prod = prodRepo.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + item.getProductId()));
            // Validate stock
            if (prod.getStock() < item.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + prod.getName());
            }
            //Create a new "cleaned" items
            SalesDetail cleanItem = new SalesDetail();
            cleanItem.setProduct(prod);
            cleanItem.setProductName(prod.getName());
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

        // Link items list to sale
        newSale.setItems(cleanItems);
        newSale.setTotal(total);

        return Mapper.toSaleDTO(saleRepo.save(newSale));
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        return Mapper.toSaleDTO(saleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found")));
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO newSaleDataDTO) {
        Sale existingSale = saleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
        //update only fields that can be updated
        if(newSaleDataDTO.getCustomerId()!=null){
            Customer custo = custoRepo.findById(newSaleDataDTO.getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            existingSale.setCustomer(custo);
        }

        if(newSaleDataDTO.getDateSale()!=null){
            existingSale.setDateSale(newSaleDataDTO.getDateSale());
        }
        return Mapper.toSaleDTO(saleRepo.save(existingSale));
    }

    @Override
    public void deleteSale(Long id) {
        if(!saleRepo.existsById(id))  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found");
        };
        saleRepo.deleteById(id);

    }

    @Override
    public List<ProductDTO> getProductsBySaleId(Long saleId) {
        //Find the sale
        Sale sale = saleRepo.findById(saleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
        List<Product> products = new ArrayList<>();
        //Extract products from sale items
        for(SalesDetail item : sale.getItems()){
            products.add(item.getProduct());
        }
        return products.stream().map(Mapper::toProductDTO).toList();
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
    public SaleDTO getSaleWithGreatestTotalAmount() {
        Sale maxSale =  saleRepo.findAll()
                .stream().max(Comparator.comparingDouble(Sale::getTotal))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No sales found"));
        return Mapper.toSaleDTO(maxSale);

    }

}
