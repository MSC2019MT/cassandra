package com.cassandra.repository;

import com.cassandra.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Optional<Employee> getEmployeeById(Long id);

    public Optional<Employee> getEmployeeByUsernameAndPassword(String username,String password);

    public Optional<List<Employee>> findAllBy();
}
