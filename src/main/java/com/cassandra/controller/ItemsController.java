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
public class ItemsController extends BaseController {

    @PostMapping(value = "/add-item/", produces = "application/json", consumes = "application/json")
    public Items addItems(@RequestBody Items items) throws Exception {
        return itemsRepository.save(items);
    }

    @GetMapping(value = "/get-item/{id}", produces = "application/json")
    public Items getItemsById(@PathVariable("id") Long id) throws Exception {
        Optional<Items> itemsOptional = itemsRepository.getItemsById(id);
        return itemsOptional != null && !itemsOptional.isEmpty() ? itemsOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-item/", produces = "application/json")
    public List<Items> getAllItems() throws Exception {
        Optional<List<Items>> itemsOptionalList = itemsRepository.findAllBy();
        return itemsOptionalList != null && !itemsOptionalList.isEmpty() ? itemsOptionalList.get() : null;
    }

    @DeleteMapping(value = "/delete-item/{id}", produces = "application/json")
    public BaseBean deleteItems(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Items> itemsOptional = itemsRepository.getItemsById(id);
        if (itemsOptional != null && !itemsOptional.isEmpty()) {
            orderItemsRepository.deleteAllByItems(itemsOptional.get());
            restaurantItemsRepository.deleteAllByItems(itemsOptional.get());
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

}
