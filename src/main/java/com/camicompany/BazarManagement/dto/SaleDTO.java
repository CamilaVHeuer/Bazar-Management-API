package com.camicompany.BazarManagement.dto;

import com.camicompany.BazarManagement.model.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long saleId;
    private LocalDate dateSale;
    private Long customerId;
    private List<SalesDetailDTO> items;
    private Double total;
    private SaleStatus status;
}
