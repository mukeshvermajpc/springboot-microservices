package com.java.employeeservice.Exception;

import com.java.employeeservice.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistException extends RuntimeException {
    private String message;

    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
