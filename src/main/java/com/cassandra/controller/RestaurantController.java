package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestuarantLoginBean;
import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController extends BaseController {

    @PostMapping(value = "/add-restaurant/", produces = "application/json", consumes = "application/json")
    public Object addRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        if (restaurant.getId() != null) {
            Optional<Restaurant> res = restaurantRepository.findTopByNameAndIdNot(restaurant.getName(), restaurant.getId());
            if (res != null && !res.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Restaurant is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        } else {
            Optional<Restaurant> res = restaurantRepository.findTopByName(restaurant.getName());
            if (res != null && !res.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Restaurant is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        }
        return restaurantRepository.save(restaurant);
    }

    @GetMapping(value = "/get-restaurant/{id}", produces = "application/json")
    public Restaurant getRestaurantById(@PathVariable("id") Long id) throws Exception {
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantById(id);
        return restaurantOptional != null && !restaurantOptional.isEmpty() ? restaurantOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-restaurant/", produces = "application/json")
    public List<Restaurant> getAllRestaurant() throws Exception {
        Optional<List<Restaurant>> restaurantOptionalList = restaurantRepository.findAllBy();
        return restaurantOptionalList != null && !restaurantOptionalList.isEmpty() ? restaurantOptionalList.get() : null
                ;
    }

    @DeleteMapping(value = "/delete-restaurant/{id}", produces = "application/json")
    public BaseBean deleteRestaurant(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantById(id);
        if (restaurantOptional != null && !restaurantOptional.isEmpty()) {
            orderRepository.deleteAll();
            visitRepository.deleteAll();
            customerRepository.deleteAll();
            foodItemRepository.deleteAll();
            tableMasterRepository.deleteAll();
            employeeRepository.deleteAll();
            restaurantRepository.delete(restaurantOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found restaurant for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
