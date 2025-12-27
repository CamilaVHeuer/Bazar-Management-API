package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.dto.CustomerDTO;
import com.camicompany.BazarManagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService custoServ;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO custoDTO) {
        CustomerDTO createdCusto= custoServ.createCustomer(custoDTO);
        return ResponseEntity.created(URI.create("/api/customer" + createdCusto.getCustomerId())).body(createdCusto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        return ResponseEntity.ok(custoServ.getAllCustomers());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){

        return ResponseEntity.ok(custoServ.getCustomerById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO custoDetailsDTO) {
        return ResponseEntity.ok(custoServ.updateCustomer(id, custoDetailsDTO));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        custoServ.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
