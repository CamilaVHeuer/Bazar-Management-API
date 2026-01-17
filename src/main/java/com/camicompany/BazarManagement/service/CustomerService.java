package com.camicompany.BazarManagement.service;

import com.camicompany.BazarManagement.dto.CustomerDTO;
import com.camicompany.BazarManagement.mapper.Mapper;
import com.camicompany.BazarManagement.model.Customer;
import com.camicompany.BazarManagement.model.CustomerStatus;
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
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(Mapper::toCustomerDTO).toList();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(customerDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer data is required");
        }

        if(customerDTO.getFirstName()==null || customerDTO.getFirstName().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if(customerDTO.getLastName()==null || customerDTO.getLastName().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }
        if(customerDTO.getDni()==null || customerDTO.getDni().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DNI is required");
        }
        if(customerRepo.existsByDni(customerDTO.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI already exists");
        }
        Customer cust = new Customer();
        cust.setFirstName(customerDTO.getFirstName());
        cust.setLastName(customerDTO.getLastName());
        cust.setDni(customerDTO.getDni());
        cust.setStatus(CustomerStatus.ACTIVE);
        return Mapper.toCustomerDTO(customerRepo.save(cust));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return Mapper.toCustomerDTO(cust);

    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDetailsDTO) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        if(cust.getStatus()!= CustomerStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only active customers can be updated");
        }
        if (customerDetailsDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer data is required");
        }

        if (customerDetailsDTO.getFirstName() != null && !customerDetailsDTO.getFirstName().trim().isEmpty()) {
            cust.setFirstName(customerDetailsDTO.getFirstName());
        }
        if (customerDetailsDTO.getLastName() != null && !customerDetailsDTO.getLastName().trim().isEmpty()) {
            cust.setLastName(customerDetailsDTO.getLastName());
        }
        if (customerDetailsDTO.getDni() != null && !customerDetailsDTO.getDni().trim().isEmpty()) {
            // Verify DNI uniqueness
            Customer existingCustomer = customerRepo.findByDni(customerDetailsDTO.getDni());
            if (existingCustomer != null && !existingCustomer.getCustomerId().equals(id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI already exists");
            }
            cust.setDni(customerDetailsDTO.getDni().trim());
        }

        return Mapper.toCustomerDTO(customerRepo.save(cust));
    }

    @Override
    public CustomerDTO inactiveCustomer(Long id) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        if( cust.getStatus() == CustomerStatus.INACTIVE){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer is already inactive");
        }
        cust.setStatus(CustomerStatus.INACTIVE);
        return Mapper.toCustomerDTO(customerRepo.save(cust));
    }

    @Override
    public CustomerDTO suspendCustomer(Long id) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        if( cust.getStatus() == CustomerStatus.SUSPENDED){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer is already suspended");
        }
        cust.setStatus(CustomerStatus.SUSPENDED);
        return Mapper.toCustomerDTO(customerRepo.save(cust));
    }

    @Override
    public CustomerDTO activateCustomer(Long id) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        if( cust.getStatus() == CustomerStatus.ACTIVE){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer is already active");
        }
        if(cust.getStatus() == CustomerStatus.SUSPENDED){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Suspended customers cannot be activated directly");
        }
        cust.setStatus(CustomerStatus.ACTIVE);
        return Mapper.toCustomerDTO(customerRepo.save(cust));
    }
}
