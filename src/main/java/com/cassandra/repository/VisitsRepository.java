package com.cassandra.repository;

import com.cassandra.beans.CustomerBean;
import com.cassandra.entities.Customer;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.entities.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface VisitsRepository extends JpaRepository<Visits, Long> {

    public Optional<Visits> getVisitsById(Long id);

    public Optional<List<Visits>> findAllBy();

    public Optional<Visits> findTopByRestaurantAndTableMasterOrderByFromDateTimeDesc(Restaurant restaurant, TableMaster tableMaster);

    @Query("Select v.id from Visits v where v.restaurant.id=:restaurantId")
    public Optional<List<Long>> getIdListByRestaurantId(Long restaurantId);

    @Query("Select v.id from Visits v where v.tableMaster.id=:tableId")
    public Optional<List<Long>> getIdListByTableId(Long tableId);

    public void deleteAllByRestaurant(Restaurant restaurant);

    public void deleteAllByTableMaster(TableMaster tableMaster);

    @Query("Select v.id from Visits v where v.customer.id=:customerId")
    public Optional<List<Long>> getIdListByCustomerId(Long customerId);

    public void deleteAllByCustomer(Customer customer);

    @Query(value = "select tbl.restaurantId,tbl.restaurantName,sum(tbl.total) as totalamount,tbl.from_date_time " +
            "from (select v.restaurant_id as restaurantId,r.name as restaurantName,v.from_Date_Time as from_date_time , (sum(oi.quantities) * ri.price_net) as total " +
            "from c_Visits v left join c_VisitOrders vo on vo.visit_id=v.id " +
            "left join c_OrderItems oi on oi.order_id=vo.order_id " +
            "left join c_restaurant r on r.id=v.restaurant_id " +
            "left join c_RestaurantItems ri on ri.item_id=oi.item_id and ri.restaurant_id=v.restaurant_id " +
            "where v.customer_id= :customerId and date_format(v.from_date_time,'%Y-%m-%d %H:%i:%s') BETWEEN :startDateTime and :endDateTime " +
            "GROUP by ri.item_id, ri.restaurant_id order by v.from_date_time asc)tbl " +
            "GROUP by tbl.restaurantId", nativeQuery = true)
    public List<Object[]> getCustomerCurrentMonthAndRestaurantDetailsByCustomerId(Long customerId, String startDateTime, String endDateTime);

}
