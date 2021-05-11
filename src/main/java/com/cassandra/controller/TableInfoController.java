package com.cassandra.controller;

import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TableInfoController extends BaseController {

    @GetMapping(value = "/get-all-table-by-restaurant/{restaurantId}", produces = "application/json")
    public List<TableMaster> getAllTableMasterByRestaurant(@PathVariable("restaurantId") Long restaurantId) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        Optional<List<TableMaster>> tableMasterOptionalList = tableMasterRepository.getTableMastersByRestaurant(restaurant);
        return tableMasterOptionalList != null && !tableMasterOptionalList.isEmpty() ? tableMasterOptionalList.get() : null;
    }
}
