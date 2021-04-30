package com.cassandra.repository;

import com.cassandra.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Optional<Customer> getCustomerById(Long id);
}
