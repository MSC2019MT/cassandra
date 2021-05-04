package com.cassandra.repository;

import com.cassandra.entities.VisitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface VisitOrderRepository extends JpaRepository<VisitOrder, Long> {

    public Optional<VisitOrder> getVisitOrderById(Long id);

    @Query("Select vo.orders.id from VisitOrder vo where vo.visits.id in :visitIdList")
    public Optional<List<Long>> getOrderIdListByVisitIdList(List<Long> visitIdList);

    @Modifying
    @Query("delete from VisitOrder where visits.id in :visitIdList")
    public void deleteAllVisitOrderByVisitsIdList(List<Long> visitIdList);

    @Query("Select vo.orders.id from VisitOrder vo where vo.visits.id = :visitId")
    public Optional<List<Long>> getOrderIdListByVisitId(Long visitId);

    @Modifying
    @Query("delete from VisitOrder where visits.id = :visitId")
    public void deleteAllVisitOrderByVisitsId(Long visitId);

    @Modifying
    @Query("delete from VisitOrder vo where vo.orders.id = :orderId")
    public void deleteAllByOrderId(Long orderId);

}
