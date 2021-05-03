package com.cassandra.repository;

import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface TableMasterRepository extends JpaRepository<TableMaster, Long> {

    public Optional<TableMaster> getTableMasterById(Long id);

    public Optional<List<TableMaster>> getTableMastersByRestaurant(Restaurant restaurant);

    public Optional<List<TableMaster>> findAllBy();

    public void deleteAllByRestaurant(Restaurant restaurant);

}
