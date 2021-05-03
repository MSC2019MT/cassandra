package com.cassandra.repository;

import com.cassandra.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    public Optional<Orders> getOrdersById(Long id);

    public Optional<List<Orders>> findAllBy();
}
