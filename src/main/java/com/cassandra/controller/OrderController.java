package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.ItemQuantitiesBean;
import com.cassandra.beans.OrderBean;
import com.cassandra.entities.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController extends BaseController {

    @PostMapping("/add-order/")
    public Orders addOrders(OrderBean orderBean) throws Exception {
        Orders orders = orderBean.getOrders();
        orders.setCreatedDateTime(new Timestamp(new Date().getTime()));
        if (orders.getId() != null) {
            Optional<Orders> ordersOptional = ordersRepository.getOrdersById(orders.getId());
            if (ordersOptional != null && !ordersOptional.isEmpty()) {
                orders.setCreatedDateTime(ordersOptional.get().getCreatedDateTime());
            }
        }
        orders = ordersRepository.save(orders);
        visitOrderRepository.deleteAllByOrderId(orders.getId());
        orderItemsRepository.deleteAllByOrderId(orders.getId());
        Visits visits = new Visits();
        visits.setId(orderBean.getVisitId());
        VisitOrder visitOrder = new VisitOrder();
        visitOrder.setOrders(orders);
        visitOrder.setVisits(visits);
        visitOrderRepository.save(visitOrder);
        if (orderBean.getItemQuantitiesBeanList() != null && !orderBean.getItemQuantitiesBeanList().isEmpty()) {
            for (ItemQuantitiesBean itemQuantitiesBean : orderBean.getItemQuantitiesBeanList()) {
                Items items = new Items();
                items.setId(itemQuantitiesBean.getItemId());
                OrderItems orderItems = new OrderItems();
                orderItems.setOrders(orders);
                orderItems.setItems(items);
                orderItems.setQuantities(itemQuantitiesBean.getQuantities());
                orderItemsRepository.save(orderItems);
            }
        }
        return orders;
    }

    @PostMapping("/get-order-by-id/")
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
            visitOrderRepository.deleteAllByOrderId(ordersOptional.get().getId());
            orderItemsRepository.deleteAllByOrderId(ordersOptional.get().getId());
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
