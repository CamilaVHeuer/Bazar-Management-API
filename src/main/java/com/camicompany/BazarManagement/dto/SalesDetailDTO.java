package com.camicompany.BazarManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailDTO {
    @Schema(description = "Unique identifier of the Sale Detail.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long saleDetailId;
    @Schema(description = "Identifier of the product.", example = "1")
    private Long productId;
    @Schema(description = "Name of the product.", example = "Glass Bottle", accessMode = Schema.AccessMode.READ_ONLY)
    private String productName;
    @Schema(description = "Quantity of the product sold.", example = "3")
    private Integer quantity;
    @Schema(description = "Unit price of the product at the time of sale.", example = "15.50", accessMode = Schema.AccessMode.READ_ONLY)
    private Double unitPrice;
    @Schema(description = "Subtotal amount for this sale detail (quantity * unit price).", example = "46.50", accessMode = Schema.AccessMode.READ_ONLY)
    private Double subTotal;

}
