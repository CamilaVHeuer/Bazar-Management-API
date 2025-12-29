package com.camicompany.BazarManagement.repository;

import com.camicompany.BazarManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByDni(String dni);

    Customer findByDni(String dni);
}
