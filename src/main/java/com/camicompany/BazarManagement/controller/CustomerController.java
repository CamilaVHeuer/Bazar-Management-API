package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.dto.CustomerDTO;
import com.camicompany.BazarManagement.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Operations for managing customers")
public class CustomerController {

    @Autowired
    private ICustomerService custoServ;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "409", description = "Duplicate DNI")
    @Operation(summary = "Create a new customer", description = "Creates a new customer and returns the created customer DTO.")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO custoDTO) {
        CustomerDTO createdCusto = custoServ.createCustomer(custoDTO);
        return ResponseEntity.created(URI.create("/api/customers/" + createdCusto.getCustomerId())).body(createdCusto);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of customers returned")
    @Operation(summary = "Get all customers", description = "Returns a list of all customers.")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        return ResponseEntity.ok(custoServ.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @Operation(summary = "Get customer by ID", description = "Returns a customer DTO by its ID.")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {

        return ResponseEntity.ok(custoServ.getCustomerById(id));
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @Operation(summary = "Update customer", description = "Updates customer data by ID and returns the updated DTO.")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO custoDetailsDTO) {
        return ResponseEntity.ok(custoServ.updateCustomer(id, custoDetailsDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @Operation(summary = "Delete customer", description = "Deletes a customer by ID.")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        custoServ.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
