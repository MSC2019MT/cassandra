package com.cassandra.repository;

import com.cassandra.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items,Long> {

    public Optional<Items> getItemsById(Long id);

    public Optional<List<Items>> findAllBy();

    public Optional<Items> getItemsByName(String name);

}
