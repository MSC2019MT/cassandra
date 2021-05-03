package com.cassandra.controller;

import com.cassandra.repository.*;
import com.cassandra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    TableMasterRepository tableMasterRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    RestaurantItemsRepository restaurantItemsRepository;

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    VisitOrderRepository visitOrderRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;
}
