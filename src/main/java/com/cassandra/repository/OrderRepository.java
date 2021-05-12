package com.cassandra.repository;

import com.cassandra.entities.Order;
import com.cassandra.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    public Optional<Order> getOrderById(Long id);

    public Optional<List<Order>> findAllBy();

    @Modifying
    @Query("delete from Order o where o.id in :orderIdList")
    public void deleteAllByIdList(@Param("orderIdList") List<Long> orderIdList);

    public void deleteAllByVisit(Visit visit);
}
