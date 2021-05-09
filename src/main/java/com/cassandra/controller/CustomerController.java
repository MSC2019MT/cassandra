package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.CustomerBean;
import com.cassandra.beans.CustomerDashBoardBean;
import com.cassandra.entities.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController extends BaseController {

    @PostMapping("/add-customer/")
    public Object addCustomer(Customer customer) throws Exception {
        if (customer.getId() != null) {
            Optional<Customer> emp = customerRepository.findTopByUsernameAndIdNot(customer.getUsername(), customer.getId());
            if (emp != null && !emp.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Username is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        } else {
            Optional<Customer> emp = customerRepository.findTopByUsername(customer.getUsername());
            if (emp != null && !emp.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Username is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        }
        return customerRepository.save(customer);
    }

    @GetMapping("/get-customer-by-id/")
    public Customer getCustomerById(Long id) throws Exception {
        Optional<Customer> customerOptional = customerRepository.getCustomerById(id);
        return customerOptional != null && !customerOptional.isEmpty() ? customerOptional.get() : null;
    }

    @GetMapping("/get-all-customer/")
    public List<Customer> getAllCustomer() throws Exception {
        Optional<List<Customer>> customerOptionalList = customerRepository.findAllBy();
        return customerOptionalList != null && !customerOptionalList.isEmpty() ? customerOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-customer/")
    public BaseBean deleteCustomer(Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Customer> customerOptional = customerRepository.getCustomerById(id);
        if (customerOptional != null && !customerOptional.isEmpty()) {
            Optional<List<Long>> visitIdListOptional = visitsRepository.getIdListByCustomerId(customerOptional.get().getId());
            if (visitIdListOptional != null && !visitIdListOptional.isEmpty() && visitIdListOptional.get() != null && !visitIdListOptional.get().isEmpty()) {
                Optional<List<Long>> orderIdList = visitOrderRepository.getOrderIdListByVisitIdList(visitIdListOptional.get());
                if (orderIdList != null && !orderIdList.isEmpty() && orderIdList.get() != null && !orderIdList.get().isEmpty()) {
                    orderItemsRepository.deleteAllByOrderIdList(orderIdList.get());
                    ordersRepository.deleteAllByIdList(orderIdList.get());
                }
                visitOrderRepository.deleteAllVisitOrderByVisitsIdList(visitIdListOptional.get());
            }
            visitsRepository.deleteAllByCustomer(customerOptional.get());
            customerRepository.delete(customerOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found customer for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }

    @PostMapping("/get-customer-dashboard-by-customer-id/")
    public CustomerDashBoardBean getCustomerDashBoardByCustomerId(Long customerId) throws Exception {
        CustomerDashBoardBean customerDashBoardBean = new CustomerDashBoardBean();
        String startDateTime = getCurrentMonthStartDateTimeStr();
        String endDateTime = getCurrentMonthEndDateTimeStr();
        List<Object[]> objArrList = visitsRepository.getCustomerCurrentMonthAndRestaurantDetailsByCustomerId(customerId, startDateTime, endDateTime);
        List<CustomerBean> customerBeanList = convertObjectListToCustomerBeanList(objArrList);
        customerDashBoardBean.setCustomerBeanList(customerBeanList);
        customerDashBoardBean.setMonthName(getCurrentMonth());
        customerDashBoardBean.setTotalAmount(getTotalAmountFromCustomerBeanList(customerBeanList));
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
