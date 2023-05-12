package com.java.employeeservice.service.impl;

import com.java.employeeservice.Exception.EmployeeAlreadyExistException;
import com.java.employeeservice.Exception.ResourceNotFoundException;
import com.java.employeeservice.dto.DepartmentDto;
import com.java.employeeservice.dto.EmployeeDepartmentDto;
import com.java.employeeservice.dto.EmployeeDto;
import com.java.employeeservice.dto.OrganizationDto;
import com.java.employeeservice.entity.Employee;
import com.java.employeeservice.mapper.EmployeeMapper;
import com.java.employeeservice.repository.EmployeeRepository;
import com.java.employeeservice.service.APIClient;
import com.java.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    EmployeeRepository employeeRepository;
    RestTemplate restTemplate;
    WebClient webClient;

    APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (existingEmployee.isPresent()) {
            throw new EmployeeAlreadyExistException("Employee already exist in database!");
        }

        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeDto(saveEmployee);
    }

    //@CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public EmployeeDepartmentDto getEmployeeById(Long id) {
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        String url = "http://localhost:8082/api/departments/" + employee.getDepartmentCode();
       /* ResponseEntity<DepartmentDto> responseEntity=restTemplate.getForEntity(url,DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();*/
        DepartmentDto departmentDto = webClient.get().uri(url).retrieve().bodyToMono(DepartmentDto.class).block();
        System.out.println("Department name is: "+departmentDto.getDepartmentName());
    //    DepartmentDto departmentDto=apiClient.getDepartment(employee.getDepartmentCode());
        String orgUrl = "http://localhost:8083/api/organization/" + employee.getOrganizationCode();
        OrganizationDto organizationDto = webClient.get().uri(orgUrl).retrieve().bodyToMono(OrganizationDto.class).block();
        EmployeeDepartmentDto employeeDto = new EmployeeDepartmentDto();
        employeeDto.setEmployee(EmployeeMapper.toEmployeeDto(employee));
        employeeDto.setDepartment(departmentDto);
        employeeDto.setOrganization(organizationDto);
        return employeeDto;
    }

    public EmployeeDepartmentDto getDefaultDepartment(Long id, Exception exception) {
        LOGGER.info("Inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD-001");
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentDescription("Research and Development Department");
        EmployeeDepartmentDto employeeDepartmentDto = new EmployeeDepartmentDto();
        employeeDepartmentDto.setEmployee(EmployeeMapper.toEmployeeDto(employee));
        employeeDepartmentDto.setDepartment(departmentDto);
        return employeeDepartmentDto;
    }

}
