package com.java.employeeservice.mapper;

import com.java.employeeservice.dto.EmployeeDto;
import com.java.employeeservice.entity.Employee;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode());
        return employee;
    }

    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode());
        return employeeDto;
    }
}
