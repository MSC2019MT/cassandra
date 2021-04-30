package com.cassandra.repository;

import com.cassandra.entities.VisitOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitOrderRepository extends JpaRepository<VisitOrder,Long> {

    public Optional<VisitOrder> getVisitOrderById(Long id);
}
