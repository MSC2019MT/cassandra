package com.cassandra.repository;

import com.cassandra.beans.RestaurantItemsBean;
import com.cassandra.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

    public Optional<OrderItems> getOrderItemsById(Long id);

    @Query("Select new  com.cassandra.beans.RestaurantItemsBean(ri.priceNet,ri.priceGross,ri.vat,oi.items.name,oi.quantities) from OrderItems oi inner join VisitOrder vo on vo.orders.id=oi.orders.id left join RestaurantItems ri on oi.items.id=ri.items.id where vo.visits.id=:visitId")
    public Optional<List<RestaurantItemsBean>> getOrderItemsByVisitId(@Param("visitId") Long visitId);

    @Modifying
    @Query("delete from OrderItems oi where oi.orders.id in :orderIdList")
    public void deleteAllByOrderIdList(@Param("orderIdList") List<Long> orderIdList);

}
