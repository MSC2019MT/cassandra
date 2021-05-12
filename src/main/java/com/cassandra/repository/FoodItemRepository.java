package com.cassandra.repository;

import com.cassandra.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    public Optional<FoodItem> getFoodItemById(Long id);

    public Optional<List<FoodItem>> findAllBy();

    public Optional<FoodItem> getFoodItemByName(String name);

}
