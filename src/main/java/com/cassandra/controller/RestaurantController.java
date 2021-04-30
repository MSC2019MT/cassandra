package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestaurantTableBean;
import com.cassandra.beans.RestuarantLoginBean;
import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import com.cassandra.repository.EmployeeRepository;
import com.cassandra.repository.RestaurantRepository;
import com.cassandra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    LoginService loginService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    @PostMapping("/login-restaurant")
    public RestuarantLoginBean restuarantLogin(RestuarantLoginBean restuarantLoginBean, HttpServletRequest request, BindingResult bindingResult) throws Exception {
        RestuarantLoginBean restuarantLoginBeanResponse = new RestuarantLoginBean();
        if (bindingResult != null) {

        }
        Optional<Employee> optionalEmployee = employeeRepository.getEmployeeByUsernameAndPassword(restuarantLoginBean.getUsername(), restuarantLoginBean.getPassword());
        if (optionalEmployee != null && !optionalEmployee.isEmpty()) {
            Employee employee = optionalEmployee.get();
            List<RestaurantTableBean> restaurantTableBeanList = loginService.getRestaurantTableBeanByRestaurant(employee.getRestaurant());
            restuarantLoginBeanResponse.setStatus("success");
            restuarantLoginBeanResponse.setEmployee(employee);
            restuarantLoginBeanResponse.setRestaurant(employee.getRestaurant());
            restuarantLoginBeanResponse.setRestaurantTableBeanList(restaurantTableBeanList);
        } else {
            List<String> errorList = new ArrayList<>();
            errorList.add("your credential is wrong please try with other credential.");
            restuarantLoginBeanResponse.setStatus("error");
            restuarantLoginBeanResponse.setErrorList(errorList);
        }
        return restuarantLoginBeanResponse;
    }

    @PostMapping("/add-restaurant/")
    public Restaurant addRestaurant(Restaurant restaurant) throws Exception {
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/get-restaurant-by-id/")
    public Restaurant getRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantById(id);
        return restaurantOptional != null && !restaurantOptional.isEmpty() ? restaurantOptional.get() : null
                ;
    }

    @GetMapping("/get-all-restaurant/")
    public List<Restaurant> getAllRestaurant() throws Exception {
        Optional<List<Restaurant>> restaurantOptionalList = restaurantRepository.findAllBy();
        return restaurantOptionalList != null && !restaurantOptionalList.isEmpty() ? restaurantOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-restaurant/")
    public BaseBean deleteRestaurant(Long id) throws Exception {
        BaseBean baseBean=new BaseBean();
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantById(id);
        if (restaurantOptional != null && !restaurantOptional.isEmpty()) {
            restaurantRepository.delete(restaurantOptional.get());
            baseBean.setStatus("success");
        }else{
            baseBean.setStatus("error");
            List<String> errorList=new ArrayList<>();
            errorList.add("Don't found restaurant for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
