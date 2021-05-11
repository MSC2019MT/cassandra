package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController extends BaseController {

    @PostMapping(value = "/add-employee/", produces = "application/json", consumes = "application/json")
    public Object addEmployee(@RequestBody Employee employee) throws Exception {
        if (employee.getId() != null) {
            Optional<Employee> emp = employeeRepository.findTopByUsernameAndIdNot(employee.getUsername(), employee.getId());
            if (emp != null && !emp.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Username is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        } else {
            Optional<Employee> emp = employeeRepository.findTopByUsername(employee.getUsername());
            if (emp != null && !emp.isEmpty()) {
                BaseBean baseBean = new BaseBean();
                List<String> errorList = new ArrayList<String>();
                errorList.add("Username is already exists");
                baseBean.setStatus("error");
                baseBean.setErrorList(errorList);
                return baseBean;
            }
        }
        return employeeRepository.save(employee);
    }

    @GetMapping(value = "/get-employee/{id}", produces = "application/json")
    public Employee getEmployeeById(@PathVariable("id") Long id) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.getEmployeeById(id);
        return employeeOptional != null && !employeeOptional.isEmpty() ? employeeOptional.get() : null;
    }

    @GetMapping(value = "/get-all-employee/", produces = "application/json")
    public List<Employee> getAllEmployee() throws Exception {
        Optional<List<Employee>> employeeOptionalList = employeeRepository.findAllBy();
        return employeeOptionalList != null && !employeeOptionalList.isEmpty() ? employeeOptionalList.get() : null
                ;
    }

    @DeleteMapping(value = "/delete-employee/{id}", produces = "application/json")
    public BaseBean deleteEmployee(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Employee> employeeOptional = employeeRepository.getEmployeeById(id);
        if (employeeOptional != null && !employeeOptional.isEmpty()) {
            employeeRepository.delete(employeeOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found employee for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
