package com.cassandra.repository;

import com.cassandra.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    public Optional<Orders> getOrdersById(Long id);

    public Optional<List<Orders>> findAllBy();

    @Modifying
    @Query("delete from Orders o where o.id in :orderIdList")
    public void deleteAllByIdList(@Param("orderIdList") List<Long> orderIdList);
}
