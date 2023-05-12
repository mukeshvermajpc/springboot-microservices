package com.java.departmentservice.controller;

import com.java.departmentservice.dto.DepartmentDto;
import com.java.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/create")
    ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{departmentCode}")
    ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode) {
        return ResponseEntity.ok(departmentService.findDepartment(departmentCode));
    }

}
