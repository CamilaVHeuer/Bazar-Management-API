package com.camicompany.BazarManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesSummaryDTO {
    @Schema(description = "Total amount of sales.", example = "1500.75", accessMode = Schema.AccessMode.READ_ONLY)
    private Double totalAmount;
    @Schema(description = "Total number of sales.", example = "100", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer totalSales;

}
