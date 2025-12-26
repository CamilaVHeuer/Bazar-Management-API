package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.model.Customer;
import com.camicompany.BazarManagement.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepo;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll(); // Fetch all customers from the repository
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer); // Save the new customer to the repository
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(null); // Fetch customer by ID or return null if not found
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer cust = customerRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        cust.setFisrtName(customerDetails.getFisrtName());
        cust.setLastName(customerDetails.getLastName());
        cust.setDni(customerDetails.getDni());

        return  customerRepo.save(cust);  }

    @Override
    public void deleteCustomer(Long id) {
        Customer cust = customerRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        customerRepo.delete(cust);

    }
}
