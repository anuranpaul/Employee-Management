package com.company.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExsistsException extends RuntimeException{
    public EmployeeAlreadyExsistsException(String message){
        super(message);
    }
}