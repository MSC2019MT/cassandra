package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestaurantItemsBean;
import com.cassandra.entities.Items;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.RestaurantItems;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantInfoController extends BaseController {

    @PostMapping(value = "/add-restaurant-item/", produces = "application/json", consumes = "application/json")
    public RestaurantItems addRestaurantItems(@RequestBody RestaurantItems restaurantItems) throws Exception {
        Optional<Items> itemsOptional = itemsRepository.getItemsByName(restaurantItems.getItems().getName());
        if (itemsOptional != null && !itemsOptional.isEmpty()) {
            restaurantItems.setItems(itemsOptional.get());
        } else {
            Items items = itemsRepository.save(restaurantItems.getItems());
            restaurantItems.setItems(items);
        }
        return restaurantItemsRepository.save(restaurantItems);
    }

    @GetMapping(value = "/get-all-item-by-restaurant/{restaurantId}", produces = "application/json")
    public List<RestaurantItemsBean> getAllItemsByRestaurant(@PathVariable("restaurantId") Long restaurantId) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        Optional<List<RestaurantItemsBean>> restaurantItemsOptionalBeans = restaurantItemsRepository.getRestaurantItemsBeanByRestaurantId(restaurantId);
        return restaurantItemsOptionalBeans != null && !restaurantItemsOptionalBeans.isEmpty() ? restaurantItemsOptionalBeans.get() : null;
    }

    @DeleteMapping(value = "/delete-restaurant-item/", produces = "application/json", consumes = "application/json")
    public BaseBean deleteRestaurantItems(@RequestBody RestaurantItems restaurantItems) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<RestaurantItems> restaurantItemsOptional = restaurantItemsRepository.getRestaurantItemsByRestaurantAndItems(restaurantItems.getRestaurant(), restaurantItems.getItems());
        if (restaurantItemsOptional != null && !restaurantItemsOptional.isEmpty()) {
            orderItemsRepository.deleteAllByItems(restaurantItemsOptional.get().getItems());
            restaurantItemsRepository.delete(restaurantItemsOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found restaurantItems for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
