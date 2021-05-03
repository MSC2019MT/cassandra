package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestaurantItemsBean;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.Items;
import com.cassandra.entities.RestaurantItems;
import com.cassandra.repository.ItemsRepository;
import com.cassandra.repository.RestaurantItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    RestaurantItemsRepository restaurantItemsRepository;

    @PostMapping("/add-item/")
    public Items addItems(Items items) throws Exception {
        return itemsRepository.save(items);
    }

    @GetMapping("/get-item-by-id/")
    public Items getItemsById(Long id) throws Exception {
        Optional<Items> itemsOptional = itemsRepository.getItemsById(id);
        return itemsOptional != null && !itemsOptional.isEmpty() ? itemsOptional.get() : null
                ;
    }

    @GetMapping("/get-all-item/")
    public List<Items> getAllItems() throws Exception {
        Optional<List<Items>> itemsOptionalList = itemsRepository.findAllBy();
        return itemsOptionalList != null && !itemsOptionalList.isEmpty() ? itemsOptionalList.get() : null;
    }

    @GetMapping("/get-all-item-by-restaurant-id/")
    public List<RestaurantItemsBean> getAllItemsByRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        Optional<List<RestaurantItemsBean>> restaurantItemsOptionalBeans = restaurantItemsRepository.getRestaurantItemsBeanByRestaurantId(restaurantId);
        return restaurantItemsOptionalBeans != null && !restaurantItemsOptionalBeans.isEmpty() ? restaurantItemsOptionalBeans.get() : null;
    }

    @PostMapping("/delete-item/")
    public BaseBean deleteItems(Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Items> itemsOptional = itemsRepository.getItemsById(id);
        if (itemsOptional != null && !itemsOptional.isEmpty()) {
            itemsRepository.delete(itemsOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found items for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }

    @PostMapping("/add-restaurant-item/")
    public RestaurantItems addRestaurantItems(RestaurantItems restaurantItems) throws Exception {
        Optional<Items> itemsOptional = itemsRepository.getItemsByName(restaurantItems.getItems().getName());
        if (itemsOptional != null && !itemsOptional.isEmpty()) {
            restaurantItems.setItems(itemsOptional.get());
        } else {
            Items items = itemsRepository.save(restaurantItems.getItems());
            restaurantItems.setItems(items);
        }
        return restaurantItemsRepository.save(restaurantItems);
    }

    @PostMapping("/delete-restaurant-item/")
    public BaseBean deleteRestaurantItems(RestaurantItems restaurantItems) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<RestaurantItems> restaurantItemsOptional = restaurantItemsRepository.getRestaurantItemsByRestaurantAndItems(restaurantItems.getRestaurant(), restaurantItems.getItems());
        if (restaurantItemsOptional != null && !restaurantItemsOptional.isEmpty()) {
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
