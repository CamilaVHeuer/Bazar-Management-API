package com.camicompany.BazarManagement.dto;

import com.camicompany.BazarManagement.model.CustomerStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @Schema(description = "Unique identifier of the customer", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long customerId;
    @Schema(description = "First name of the customer", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    @Schema(description = "Last name of the customer", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;
    @Schema(description = "DNI of the customer", example = "12345678A", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dni;
    @Schema(description = "Status of the customer", example = "ACTIVE", accessMode = Schema.AccessMode.READ_ONLY)
    private CustomerStatus status;
}
