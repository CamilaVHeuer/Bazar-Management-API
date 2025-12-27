package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.CustomerDTO;
import com.camicompany.BazarManagement.model.Customer;

import java.util.List;

public interface ICustomerService {

    //READ ALL
    public List<CustomerDTO> getAllCustomers();

    //CREATE
    public CustomerDTO createCustomer(CustomerDTO customerDTO);

    //READ BY ID
    public CustomerDTO getCustomerById(Long id);

    //UPDATE
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDetailsDTO);

    //DELETE
    public void deleteCustomer(Long id);
}
