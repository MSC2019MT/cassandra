package com.cassandra.repository;

import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.entities.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitsRepository extends JpaRepository<Visits, Long> {

    public Optional<Visits> getVisitsById(Long id);

    public Optional<List<Visits>> findAllBy();

    public Optional<Visits> findTopByRestaurantAndTableMasterOrderByFromDateTimeDesc(Restaurant restaurant,TableMaster tableMaster);

}
