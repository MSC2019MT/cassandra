package com.cassandra.repository;

import com.cassandra.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    public Optional<Restaurant> getRestaurantById(Long id);
}
