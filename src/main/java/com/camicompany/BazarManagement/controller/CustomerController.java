package com.camicompany.BazarManagement.controller;

import com.camicompany.BazarManagement.model.Customer;
import com.camicompany.BazarManagement.model.Product;
import com.camicompany.BazarManagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService custoServ;

    @PostMapping("/customers/create")
    public Customer createCustomer(@RequestBody Customer custo) {

        return custoServ.createCustomer(custo);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return custoServ.getAllCustomers();
    }

    @GetMapping ("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){

        return custoServ.getCustomerById(id);
    }

    @PutMapping ("/customers/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer custoDetails) {
        return custoServ.updateCustomer(id, custoDetails);
    }

    @DeleteMapping ("/customers/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        custoServ.deleteCustomer(id);
    }

}
