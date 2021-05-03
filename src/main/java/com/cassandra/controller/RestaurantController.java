package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestuarantLoginBean;
import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController extends BaseController {

    @PostMapping("/login-restaurant")
    public RestuarantLoginBean restuarantLogin(RestuarantLoginBean restuarantLoginBean, HttpServletRequest request, BindingResult bindingResult) throws Exception {
        RestuarantLoginBean restuarantLoginBeanResponse = new RestuarantLoginBean();
        if (bindingResult != null) {

        }
        Optional<Employee> optionalEmployee = employeeRepository.getEmployeeByUsernameAndPassword(restuarantLoginBean.getUsername(), restuarantLoginBean.getPassword());
        if (optionalEmployee != null && !optionalEmployee.isEmpty()) {
            Employee employee = optionalEmployee.get();
            restuarantLoginBeanResponse.setStatus("success");
            restuarantLoginBeanResponse.setEmployee(employee);
            restuarantLoginBeanResponse.setRestaurant(employee.getRestaurant());
            Optional<List<TableMaster>> tableMasterList = tableMasterRepository.getTableMastersByRestaurant(employee.getRestaurant());
            restuarantLoginBeanResponse.setTableMasterList(tableMasterList != null && !tableMasterList.isEmpty() ? tableMasterList.get() : null);
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

    @PostMapping("/get-restaurant-by-id/")
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
        BaseBean baseBean = new BaseBean();
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantById(id);
        if (restaurantOptional != null && !restaurantOptional.isEmpty()) {
            Optional<List<Long>> visitIdListOptional = visitsRepository.getIdListByRestaurantId(restaurantOptional.get().getId());
            if (visitIdListOptional != null && !visitIdListOptional.isEmpty() && visitIdListOptional.get() != null && !visitIdListOptional.get().isEmpty()) {
                Optional<List<Long>> orderIdList = visitOrderRepository.getOrderIdListByVisitIdList(visitIdListOptional.get());
                if (orderIdList != null && !orderIdList.isEmpty() && orderIdList.get() != null && !orderIdList.get().isEmpty()) {
                    orderItemsRepository.deleteAllByOrderIdList(orderIdList.get());
                    ordersRepository.deleteAllByIdList(orderIdList.get());
                }
                visitOrderRepository.deleteAllVisitOrderByVisitsId(visitIdListOptional.get());
            }
            visitsRepository.deleteAllByRestaurant(restaurantOptional.get());
            restaurantItemsRepository.deleteAllByRestaurant(restaurantOptional.get());
            tableMasterRepository.deleteAllByRestaurant(restaurantOptional.get());
            employeeRepository.deleteAllByRestaurant(restaurantOptional.get());
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
