package com.camicompany.BazarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailDTO {
    private Long saleId;
    private Double totalSaleAmount;
    private int totalProd;
    private String customerName;
    private String customerLastName;


}
