package com.cassandra.beans;


import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;

import java.util.List;

public class RestuarantLoginBean extends LoginBaseBean {

    private Employee employee;

    private Restaurant restaurant;

    private List<TableMaster> tableMasterList;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<TableMaster> getTableMasterList() {
        return tableMasterList;
    }

    public void setTableMasterList(List<TableMaster> tableMasterList) {
        this.tableMasterList = tableMasterList;
    }
}
