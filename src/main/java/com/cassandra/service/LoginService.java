package com.cassandra.service;

import com.cassandra.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TableMasterRepository tableMasterRepository;

    @Autowired
    ItemRepository itemRepository;

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

    @Autowired
    RoleRepository roleRepository;

}
