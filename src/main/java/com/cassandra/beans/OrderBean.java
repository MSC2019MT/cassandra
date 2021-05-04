package com.cassandra.beans;

import com.cassandra.entities.Orders;

import java.util.List;

public class OrderBean extends BaseBean {

    private Orders orders;
    private List<ItemQuantitiesBean> itemQuantitiesBeanList;
    private Long visitId;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<ItemQuantitiesBean> getItemQuantitiesBeanList() {
        return itemQuantitiesBeanList;
    }

    public void setItemQuantitiesBeanList(List<ItemQuantitiesBean> itemQuantitiesBeanList) {
        this.itemQuantitiesBeanList = itemQuantitiesBeanList;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }
}
