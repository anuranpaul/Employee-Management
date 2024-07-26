package com.company.employee.service;

import com.company.employee.entity.Employee;
import com.company.employee.exception.EmployeeAlreadyExsistsException;
import com.company.employee.exception.ResourceNotFoundException;
import com.company.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {

    private static final Logger logger = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee){
        logger.info("Adding employee : {}", employee.getEmployeeId());
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        if(employeeOptional.isPresent()){
            throw new EmployeeAlreadyExsistsException("Employee already exists with given Employee ID"
                    + employee.getEmployeeId());
        }
        employeeRepository.save(employee);
    }

    /**
     * @param employee - input Employee body to be updated
     * @return true or false, indicating whether updation is successful
     */
    @Override
    @Transactional
    public boolean updateEmployee(Employee employee) {
        boolean isUpdated = false;
        if (employee != null) {
            String empId= employee.getEmployeeId();
            Employee existingEmployee = employeeRepository.findByEmployeeId(empId)
                    .orElseThrow(()->new ResourceNotFoundException("Employee", "Employee iD",empId));
            existingEmployee.setEmployeeId(employee.getEmployeeId());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setExperience(employee.getExperience());
            employeeRepository.save(existingEmployee);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param employeeId - input employee ID of the employee to be deleted
     * @return true or false, indicating whether deletion is successful
     */
    @Transactional
    @Override
    public boolean deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee","Employee ID",employeeId));
        employeeRepository.deleteByEmployeeId(employeeId);
        logger.info("Deleting employee with Employee ID", employeeId);
        return true;
    }

    /**
     * @param employeeId - input employeeID
     * @return - details of the corresponding employee ID
     */
    @Override
    public Employee getEmployee(String employeeId) {
        logger.info("Finding employee with employee ID", employeeId);
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee","Employee ID",employeeId));
        return employee;
    }

    /**
     * @return - List of all employees
     */
    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching list of all Employees");
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

}
