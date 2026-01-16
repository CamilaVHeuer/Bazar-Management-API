package com.camicompany.BazarManagement.dto;

import com.camicompany.BazarManagement.model.SaleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Unique identifier of the Sale.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long saleId;
    @Schema(description = "Date of the Sale.", example = "2025-06-15")
    private LocalDate dateSale;
    @Schema(description = "Identifier of the associated Customer.", example = "1")
    private Long customerId;
    @Schema(description = "Details of the items sold in this Sale.")
    private List<SalesDetailDTO> items;
    @Schema(description = "Total amount of the Sale.", example = "2500.00", accessMode = Schema.AccessMode.READ_ONLY)
    private Double total;
    @Schema(description = "Status of the Sale.", example = "CREATED", accessMode = Schema.AccessMode.READ_ONLY)
    private SaleStatus status;
}
