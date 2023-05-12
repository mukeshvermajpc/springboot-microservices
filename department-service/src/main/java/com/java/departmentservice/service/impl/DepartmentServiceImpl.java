package com.java.departmentservice.service.impl;

import com.java.departmentservice.Mapper.DepartmentMapper;
import com.java.departmentservice.dto.DepartmentDto;
import com.java.departmentservice.entity.Department;
import com.java.departmentservice.exception.DepartmentAlreadyExist;
import com.java.departmentservice.exception.DepartmentNotFound;
import com.java.departmentservice.repository.DepartmentRepository;
import com.java.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Optional<Department> dept = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
        if (dept.isPresent()) {
            throw new DepartmentAlreadyExist("Department Already Exist");
        }
        DepartmentMapper departmentMapper = new DepartmentMapper();
        Department department = departmentMapper.toDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto findDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(() -> new DepartmentNotFound("Department", "Id", departmentCode));
        DepartmentMapper departmentMapper = new DepartmentMapper();
        DepartmentDto departmentDto = departmentMapper.toDepartmentDto(department);
        return departmentDto;
    }
}
