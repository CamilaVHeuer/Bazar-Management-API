package com.camicompany.BazarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailDTO {
    private Long saleDetailId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;

}
