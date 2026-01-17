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

    //UPDATE STATUS TO INACTIVE
    public CustomerDTO inactiveCustomer(Long id);

    //UPDATE STATUS TO SUSPENDED
    public CustomerDTO suspendCustomer(Long id);

    //UPDATE STATUS TO ACTIVE
    public CustomerDTO activateCustomer(Long id);
}
