package com.cassandra.repository;

import com.cassandra.entities.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableStatusRepository extends JpaRepository<TableStatus,Long> {

    public Optional<TableStatus> getTableStatusById(Long id);
}
