package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController extends BaseController {

    @PostMapping("/add-employee/")
    public Object addEmployee(Employee employee) throws Exception {
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

    @PostMapping("/get-employee-by-id/")
    public Employee getEmployeeById(Long id) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.getEmployeeById(id);
        return employeeOptional != null && !employeeOptional.isEmpty() ? employeeOptional.get() : null;
    }

    @GetMapping("/get-all-employee/")
    public List<Employee> getAllEmployee() throws Exception {
        Optional<List<Employee>> employeeOptionalList = employeeRepository.findAllBy();
        return employeeOptionalList != null && !employeeOptionalList.isEmpty() ? employeeOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-employee/")
    public BaseBean deleteEmployee(Long id) throws Exception {
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
