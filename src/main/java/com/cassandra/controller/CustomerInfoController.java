package com.cassandra.controller;

import com.cassandra.beans.CustomerBean;
import com.cassandra.beans.CustomerDashBoardBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerInfoController extends BaseController {


    @GetMapping("/get-customer-dashboard/{id}")
    public CustomerDashBoardBean getCustomerDashBoardByCustomerId(@PathVariable("id") Long customerId) throws Exception {
        CustomerDashBoardBean customerDashBoardBean = new CustomerDashBoardBean();
        System.out.println("test");
        String startDateTime = getCurrentMonthStartDateTimeStr();
        String endDateTime = getCurrentMonthEndDateTimeStr();
        /*List<Object[]> objArrList = visitRepository.getCustomerCurrentMonthAndRestaurantDetailsByCustomerId(customerId, startDateTime, endDateTime);
        List<CustomerBean> customerBeanList = convertObjectListToCustomerBeanList(objArrList);
        customerDashBoardBean.setCustomerBeanList(customerBeanList);
        customerDashBoardBean.setTotalAmount(getTotalAmountFromCustomerBeanList(customerBeanList));*/
        customerDashBoardBean.setMonthName(getCurrentMonth());
        return customerDashBoardBean;
    }

    public List<CustomerBean> convertObjectListToCustomerBeanList(List<Object[]> objectList) throws Exception {
        List<CustomerBean> customerBeanList = new ArrayList<>();
        if (objectList != null && objectList.size() > 0) {
            for (Object[] objArr : objectList) {
                CustomerBean customerBean = new CustomerBean();
                if (objArr[0] != null) {
                    String restaurantIdStr = objArr[0].toString();
                    Long restaurantId = Long.parseLong(restaurantIdStr);
                    customerBean.setRestaurantId(restaurantId);
                }
                if (objArr[1] != null) {
                    String restaurantName = objArr[1].toString();
                    customerBean.setRestaurantName(restaurantName);
                }
                if (objArr[2] != null) {
                    String totalStr = objArr[2].toString();
                    Float total = Float.parseFloat(totalStr);
                    customerBean.setAmount(total);
                }
                if (objArr[3] != null) {
                    Date fromDate = (Date) objArr[3];
                    DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm");
                    String fromDateStr = dateFormat.format(fromDate);
                    customerBean.setFromDateStr(fromDateStr);
                }
                customerBeanList.add(customerBean);
            }
        }
        return customerBeanList;
    }

    public Float getTotalAmountFromCustomerBeanList(List<CustomerBean> customerBeanList) {
        Float totalAmount = 0f;
        if (customerBeanList != null && !customerBeanList.isEmpty()) {
            for (CustomerBean customerBean : customerBeanList) {
                if (customerBean.getAmount() != null) {
                    totalAmount = totalAmount + customerBean.getAmount();
                }
            }
        }
        return totalAmount;
    }
}
