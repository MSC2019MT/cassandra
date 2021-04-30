package com.cassandra.repository;

import com.cassandra.entities.Employee;
import com.cassandra.entities.TableMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableMasterRepository extends JpaRepository<TableMaster,Long> {

    public Optional<TableMaster> getTableMasterById(Long id);
}
