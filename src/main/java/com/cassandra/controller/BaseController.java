package com.cassandra.controller;

import com.cassandra.repository.*;
import com.cassandra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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

    public String getCurrentMonth() {
        LocalDate currentdate = LocalDate.now();
        return currentdate.getMonth().toString();
    }

    public String getCurrentMonthStartDateTimeStr() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String startDateStr = dateFormat.format(new Date());
        startDateStr = startDateStr + "-01 00:00:00";
        return startDateStr;
    }

    public String getCurrentMonthEndDateTimeStr() {
        Calendar cal = Calendar.getInstance();
        int endDay = cal.getActualMaximum(Calendar.DATE);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String endDateStr = dateFormat.format(new Date());
        endDateStr = endDateStr + "-" + endDay + " 23:59:59";
        return endDateStr;
    }

}
