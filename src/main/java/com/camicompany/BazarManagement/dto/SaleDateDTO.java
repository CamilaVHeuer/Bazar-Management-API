package com.camicompany.BazarManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDateDTO {
    @Schema(description = "Date of the Sale.", example = "2025-06-15")
    private LocalDate date;
}
