package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.FoodItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FoodFoodItemController extends BaseController {

    @PostMapping(value = "/add-fooditem/", produces = "application/json", consumes = "application/json")
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) throws Exception {
        return foodItemRepository.save(foodItem);
    }

    @GetMapping(value = "/get-fooditem/{id}", produces = "application/json")
    public FoodItem getFoodItemById(@PathVariable("id") Long id) throws Exception {
        Optional<FoodItem> itemOptional = foodItemRepository.getFoodItemById(id);
        return itemOptional != null && !itemOptional.isEmpty() ? itemOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-fooditem/", produces = "application/json")
    public List<FoodItem> getAllFoodItem() throws Exception {
        Optional<List<FoodItem>> itemOptionalList = foodItemRepository.findAllBy();
        return itemOptionalList != null && !itemOptionalList.isEmpty() ? itemOptionalList.get() : null;
    }

    @DeleteMapping(value = "/delete-fooditem/{id}", produces = "application/json")
    public BaseBean deleteFoodItem(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<FoodItem> itemOptional = foodItemRepository.getFoodItemById(id);
        if (itemOptional != null && !itemOptional.isEmpty()) {
            foodItemRepository.delete(itemOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found item for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }

}
