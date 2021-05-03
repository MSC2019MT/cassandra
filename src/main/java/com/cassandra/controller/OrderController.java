package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Orders;
import com.cassandra.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrdersRepository ordersRepository;

    @PostMapping("/add-order/")
    public Orders addOrders(Orders orders) throws Exception {
        orders.setCreatedDateTime(new Timestamp(new Date().getTime()));
        if (orders.getId() != null) {
            Optional<Orders> ordersOptional = ordersRepository.getOrdersById(orders.getId());
            if (ordersOptional != null && !ordersOptional.isEmpty()) {
                orders.setCreatedDateTime(ordersOptional.get().getCreatedDateTime());
            }
        }
        return ordersRepository.save(orders);
    }

    @GetMapping("/get-order-by-id/")
    public Orders getOrdersById(Long id) throws Exception {
        Optional<Orders> ordersOptional = ordersRepository.getOrdersById(id);
        return ordersOptional != null && !ordersOptional.isEmpty() ? ordersOptional.get() : null
                ;
    }

    @GetMapping("/get-all-orders/")
    public List<Orders> getAllOrders() throws Exception {
        Optional<List<Orders>> ordersOptionalList = ordersRepository.findAllBy();
        return ordersOptionalList != null && !ordersOptionalList.isEmpty() ? ordersOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-orders/")
    public BaseBean deleteOrders(Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Orders> ordersOptional = ordersRepository.getOrdersById(id);
        if (ordersOptional != null && !ordersOptional.isEmpty()) {
            ordersRepository.delete(ordersOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found orders for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
