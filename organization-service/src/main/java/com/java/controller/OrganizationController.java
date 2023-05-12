package com.java.controller;

import com.java.dto.OrganizationDto;
import com.java.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping("/create")
    ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{organizationCode}")
    ResponseEntity<OrganizationDto> getOrganization(@PathVariable String organizationCode) {
        return ResponseEntity.ok(organizationService.findOrganization(organizationCode));
    }
}
