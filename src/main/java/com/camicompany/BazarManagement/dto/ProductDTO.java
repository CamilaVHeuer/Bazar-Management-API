package com.camicompany.BazarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String brand;
    private Double unitPrice;
    private Integer stock;
}
