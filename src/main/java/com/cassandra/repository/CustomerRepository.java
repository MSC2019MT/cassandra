package com.cassandra.repository;

import com.cassandra.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> getCustomerById(Long id);

    public Optional<Customer> findTopByUsername(String username);

    public Optional<Customer> findTopByUsernameAndIdNot(String username, Long id);

    public Optional<List<Customer>> findAllBy();

}
