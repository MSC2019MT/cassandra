package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.CustomerBean;
import com.cassandra.beans.CustomerDashBoardBean;
import com.cassandra.entities.Customer;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController extends BaseController {

    @PostMapping(value = "/add-customer/", produces = "application/json", consumes = "application/json")
    public Object addCustomer(@RequestBody Customer customer) throws Exception {
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

    @GetMapping(value = "/get-customer/{id}", produces = "application/json")
    public Customer getCustomerById(@PathVariable("id") Long id) throws Exception {
        Optional<Customer> customerOptional = customerRepository.getCustomerById(id);
        return customerOptional != null && !customerOptional.isEmpty() ? customerOptional.get() : null;
    }

    @GetMapping(value = "/get-all-customer/", produces = "application/json")
    public List<Customer> getAllCustomer() throws Exception {
        Optional<List<Customer>> customerOptionalList = customerRepository.findAllBy();
        return customerOptionalList != null && !customerOptionalList.isEmpty() ? customerOptionalList.get() : null
                ;
    }

    @DeleteMapping(value = "/delete-customer/{id}", produces = "application/json")
    public BaseBean deleteCustomer(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Customer> customerOptional = customerRepository.getCustomerById(id);
        if (customerOptional != null && !customerOptional.isEmpty()) {
            visitRepository.deleteAllByCustomer(customerOptional.get());
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

}
