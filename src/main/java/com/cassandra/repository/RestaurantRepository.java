package com.cassandra.repository;

import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Optional<Restaurant> getRestaurantById(Long id);

    public Optional<List<Restaurant>> findAllBy();

    public Optional<Restaurant> findTopByName(String username);

    public Optional<Restaurant> findTopByNameAndIdNot(String username, Long id);
}
