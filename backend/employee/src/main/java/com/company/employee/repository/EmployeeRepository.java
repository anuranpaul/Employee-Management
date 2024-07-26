package com.company.employee.repository;

import com.company.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(String employeeID);

    void deleteByEmployeeId(String employeeId);
}
