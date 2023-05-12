package com.java.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentAlreadyExist extends RuntimeException {
    private String message;
    public DepartmentAlreadyExist(String message){
        super(message);
    }
}

