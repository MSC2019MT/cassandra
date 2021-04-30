package com.cassandra.repository;

import com.cassandra.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items,Long> {

    public Optional<Items> getItemsById(Long id);
}
