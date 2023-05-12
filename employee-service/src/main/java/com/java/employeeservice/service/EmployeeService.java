package com.java.employeeservice.service;

import com.java.employeeservice.dto.EmployeeDepartmentDto;
import com.java.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDepartmentDto getEmployeeById(Long id);
}
