package com.camicompany.BazarManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Schema(description = "Unique identifier of the Product.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long productId;
    @Schema(description = "Name of the Product.", example = "Glass Bottle", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Brand of the Product.", example = "EcoBrand", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brand;
    @Schema(description = "Unit price of the Product.", example = "15.50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double unitPrice;
    @Schema(description = "Available stock of the Product.", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stock;
}
