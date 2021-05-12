package com.cassandra.repository;

import com.cassandra.entities.Employee;
import com.cassandra.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> getEmployeeById(Long id);

    public Optional<Employee> getEmployeeByUsernameAndPassword(String username, String password);

    public Optional<List<Employee>> findAllBy();

    public Optional<Employee> findTopByUsername(String username);

    public Optional<Employee> findTopByUsernameAndIdNot(String username, Long id);

}
