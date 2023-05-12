package com.java.departmentservice.service;

import com.java.departmentservice.dto.DepartmentDto;
import com.java.departmentservice.entity.Department;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartment(String departmentCode);
}
