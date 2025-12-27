package com.camicompany.BazarManagement.mapper;

import com.camicompany.BazarManagement.dto.CustomerDTO;
import com.camicompany.BazarManagement.dto.ProductDTO;
import com.camicompany.BazarManagement.dto.SaleDTO;
import com.camicompany.BazarManagement.dto.SalesDetailDTO;
import com.camicompany.BazarManagement.model.Customer;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.model.Sale;
import com.camicompany.BazarManagement.model.SalesDetail;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    // This class include mapping methods between DTOs and entities
    // Customer to CustomerDTO
    public static CustomerDTO toCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setDni(customer.getDni());
        return customerDTO;
    }

    // Product to ProductDTO
    public static ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setStock(product.getStock());
        return productDTO;
    }

    // SalesDetail to SalesDetailDTO
    public static SalesDetailDTO toSalesDetailDTO(SalesDetail salesDetail) {
        if (salesDetail == null) {
            return null;
        }
        SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
        salesDetailDTO.setSaleDetailId(salesDetail.getSaleDetailId());
        salesDetailDTO.setProductId(salesDetail.getProduct().getProductId());
        salesDetailDTO.setProductName(salesDetail.getProductName());
        salesDetailDTO.setQuantity(salesDetail.getQuantity());
        salesDetailDTO.setUnitPrice(salesDetail.getUnitPrice());
        salesDetailDTO.setSubTotal(salesDetail.getSubTotal());
        return salesDetailDTO;
    }

    // Sale to SaleDTO
    public static SaleDTO toSaleDTO(Sale sale) {
        if (sale == null) {
            return null;
        }

        // Convert the list de SalesDetail a SalesDetailDTO
        List<SalesDetailDTO> itemsDTO = new ArrayList<>();
        if (sale.getItems() != null) {
            for (SalesDetail item : sale.getItems()) {
                itemsDTO.add(toSalesDetailDTO(item));
            }
        }

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setDateSale(sale.getDateSale());
        saleDTO.setCustomerId(sale.getCustomer().getCustomerId());
        saleDTO.setItems(itemsDTO);
        saleDTO.setTotal(sale.getTotal());

        return saleDTO;
    }

}
