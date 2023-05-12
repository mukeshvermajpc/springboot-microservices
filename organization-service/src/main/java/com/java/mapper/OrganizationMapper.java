package com.java.mapper;

import com.java.dto.OrganizationDto;
import com.java.entity.Organization;

public class OrganizationMapper {
    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setCreatedDt(organizationDto.getCreatedDt());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        return organization;
    }

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setOrganizationName(organization.getOrganizationName());
        organizationDto.setOrganizationDescription(organization.getOrganizationDescription());
        organizationDto.setCreatedDt(organization.getCreatedDt());
        organizationDto.setOrganizationCode(organization.getOrganizationCode());
        return organizationDto;
    }
}
