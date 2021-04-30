package com.cassandra.repository;

import com.cassandra.entities.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitsRepository extends JpaRepository<Visits,Long> {

    public Optional<Visits> getVisitsById(Long id);
}
