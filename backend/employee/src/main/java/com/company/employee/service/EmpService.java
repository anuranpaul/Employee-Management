package com.company.employee.service;

import com.company.employee.entity.Employee;

import java.util.List;

public interface EmpService {


    /**
     *
     * @return - List of all employees
     */
    List<Employee> getAllEmployees();

    /**
     * @param employee - JSON body of Employee details, that is to be added
     */
    void addEmployee(Employee employee);

    /**
     *
     * @param employee - input Employee body to be updated
     * @return true or false, indicating whether updation is successful
     */
    boolean updateEmployee(Employee employee);

    /**
     *
     * @param employeeId - input employee ID of the employee to be deleted
     * @return true or false, indicating whether deletion is successful
     */
    boolean deleteEmployee(String employeeId);


    /**
     *
     * @param employeeId - input employeeID
     * @return - details of the corresponding employee ID
     */
    Employee getEmployee(String employeeId);
}
