package com.java.departmentservice.Mapper;

import com.java.departmentservice.dto.DepartmentDto;
import com.java.departmentservice.entity.Department;

public class DepartmentMapper {
    public Department toDepartment(DepartmentDto departmentDto) {
        Department department = new Department(departmentDto.getId(), departmentDto.getDepartmentName(), departmentDto.getDepartmentDescription()
                , departmentDto.getDepartmentCode());
        return department;
    }

    public DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto(department.getId(), department.getDepartmentName(), department.getDepartmentDescription(), department.getDepartmentCode());
        return departmentDto;
    }
}
