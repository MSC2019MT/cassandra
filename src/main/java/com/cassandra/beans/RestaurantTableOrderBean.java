package com.cassandra.beans;

import com.cassandra.entities.Customer;
import com.cassandra.entities.Orders;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;

import java.util.List;

public class RestaurantTableOrderBean {

    private TableMaster tableMaster;
    private Restaurant restaurant;
    private List<RestaurantItemsBean> restaurantItemsBeanList;
    private Float total;
    private Customer customer;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public TableMaster getTableMaster() {
        return tableMaster;
    }

    public void setTableMaster(TableMaster tableMaster) {
        this.tableMaster = tableMaster;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<RestaurantItemsBean> getRestaurantItemsBeanList() {
        return restaurantItemsBeanList;
    }

    public void setRestaurantItemsBeanList(List<RestaurantItemsBean> restaurantItemsBeanList) {
        this.restaurantItemsBeanList = restaurantItemsBeanList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
