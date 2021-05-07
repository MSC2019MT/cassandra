package com.cassandra.beans;

import java.util.List;

public class CustomerDashBoardBean extends BaseBean {

    private String monthName;
    private List<CustomerBean> customerBeanList;
    private Float totalAmount;

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public List<CustomerBean> getCustomerBeanList() {
        return customerBeanList;
    }

    public void setCustomerBeanList(List<CustomerBean> customerBeanList) {
        this.customerBeanList = customerBeanList;
    }
}
