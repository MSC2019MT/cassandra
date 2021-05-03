package com.cassandra.repository;

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
}
