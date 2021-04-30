package com.cassandra.repository;

import com.cassandra.entities.RestaurantItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantItemsRepository extends JpaRepository<RestaurantItems,Long> {

    public Optional<RestaurantItems> getRestaurantItemsById(Long id);
}
