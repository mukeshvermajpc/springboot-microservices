package com.java.service;

import com.java.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto findOrganization(String organizationCode);
}
