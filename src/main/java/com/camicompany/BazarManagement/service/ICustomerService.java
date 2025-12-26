package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.model.Customer;

import java.util.List;

public interface ICustomerService {

    //READ ALL
    public List<Customer> getAllCustomers();

    //CREATE
    public Customer createCustomer(Customer customer);

    //READ BY ID
    public Customer getCustomerById(Long id);

    //UPDATE
    public Customer updateCustomer(Long id, Customer customerDetails);

    //DELETE
    public void deleteCustomer(Long id);
}
