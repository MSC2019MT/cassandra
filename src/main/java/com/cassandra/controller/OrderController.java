package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.ItemQuantitiesBean;
import com.cassandra.beans.OrderBean;
import com.cassandra.entities.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController extends BaseController {

    @PostMapping(value = "/add-order/", produces = "application/json", consumes = "application/json")
    public Order addOrder(@RequestBody Order order) throws Exception {
        order.setCreatedDateTime(new Timestamp(new Date().getTime()));
        if (order.getId() != null) {
            Optional<Order> orderOptional = orderRepository.getOrderById(order.getId());
            if (orderOptional != null && !orderOptional.isEmpty()) {
                order.setCreatedDateTime(orderOptional.get().getCreatedDateTime());
            }
        }
        return orderRepository.save(order);
    }

    @GetMapping(value = "/get-order/{id}", produces = "application/json")
    public Order getOrderById(@PathVariable("id") Long id) throws Exception {
        Optional<Order> orderOptional = orderRepository.getOrderById(id);
        return orderOptional != null && !orderOptional.isEmpty() ? orderOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-order/", produces = "application/json")
    public List<Order> getAllOrder() throws Exception {
        Optional<List<Order>> orderOptionalList = orderRepository.findAllBy();
        return orderOptionalList != null && !orderOptionalList.isEmpty() ? orderOptionalList.get() : null
                ;
    }

    @DeleteMapping(value = "/delete-order/{id}", produces = "application/json")
    public BaseBean deleteOrder(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Order> orderOptional = orderRepository.getOrderById(id);
        if (orderOptional != null && !orderOptional.isEmpty()) {
            orderRepository.delete(orderOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found order for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
