package com.cassandra.repository;

import com.cassandra.beans.RestaurantItemsBean;
import com.cassandra.entities.Items;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.RestaurantItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface RestaurantItemsRepository extends JpaRepository<RestaurantItems, Long> {

    public Optional<RestaurantItems> getRestaurantItemsById(Long id);

    @Query("select new com.cassandra.beans.RestaurantItemsBean(ri.restaurant.id,ri.items,ri.priceGross,ri.priceNet,ri.vat) from RestaurantItems ri where ri.restaurant.id=:restaurantId")
    public Optional<List<RestaurantItemsBean>> getRestaurantItemsBeanByRestaurantId(@Param("restaurantId") Long restaurantId);

    public Optional<RestaurantItems> getRestaurantItemsByRestaurantAndItems(Restaurant restaurant, Items items);

    public void deleteAllByRestaurant(Restaurant restaurant);

    public void deleteAllByItems(Items items);

}
