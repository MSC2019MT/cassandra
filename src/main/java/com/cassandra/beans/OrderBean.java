package com.cassandra.beans;

import com.cassandra.entities.Order;

import java.util.List;

public class OrderBean extends BaseBean {

    private Order order;
    private List<ItemQuantitiesBean> itemQuantitiesBeanList;
    private Long visitId;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
