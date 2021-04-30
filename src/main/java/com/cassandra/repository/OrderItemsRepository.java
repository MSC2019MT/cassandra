package com.cassandra.repository;

import com.cassandra.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {

    public Optional<OrderItems> getOrderItemsById(Long id);
}
