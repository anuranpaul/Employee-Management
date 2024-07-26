package com.company.employee.controller;

import com.company.employee.constants.EmployeeConstants;
import com.company.employee.dto.ResponseDto;
import com.company.employee.entity.Employee;
import com.company.employee.service.EmpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4300/")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmpService empService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addEmployee(@Valid @RequestBody Employee employee){
        logger.info("Adding user - accessed by controller class : {}", employee.getEmployeeId());
        empService.addEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(EmployeeConstants.STATUS_201,EmployeeConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployee(@Valid @RequestBody Employee employee){

        boolean isUpdated = empService.updateEmployee(employee);
        if(isUpdated){
            logger.info("Updating user with Employee ID : {}",employee.getEmployeeId() );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(EmployeeConstants.STATUS_200, EmployeeConstants.MESSAGE_200));
        }else{
            logger.warn("Failed to update");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_UPDATE));
        }
    }

    @GetMapping("/view/{employeeId}")
    public ResponseEntity<Employee> getEmployeebyId(@PathVariable String employeeId){
        logger.info("Viewing employee by Employee ID : {}",employeeId);
        Employee emp = empService.getEmployee(employeeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(emp);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        logger.info("Fetching all users :");
        List<Employee> employees = empService.getAllEmployees();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employees);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable String employeeId){
        boolean isDeleted = empService.deleteEmployee(employeeId);
        if(isDeleted){
            logger.info("Deleting Employee record with Employee ID : {}",employeeId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(EmployeeConstants.STATUS_200, EmployeeConstants.MESSAGE_200));
        }else{
            logger.warn("Failed to delete");
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_DELETE));
        }
    }
}
