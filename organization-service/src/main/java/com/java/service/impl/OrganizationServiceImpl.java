package com.java.service.impl;

        import com.java.dto.OrganizationDto;
        import com.java.entity.Organization;
        import com.java.exception.OrganizationAlreadyExistException;
        import com.java.exception.ResourceNotFoundException;
        import com.java.mapper.OrganizationMapper;
        import com.java.repository.OrganizationRepository;
        import com.java.service.OrganizationService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Optional<Organization> existingOrg = organizationRepository.findByOrganizationCode(organizationDto.getOrganizationCode());
        if (existingOrg.isPresent()) {
            throw new OrganizationAlreadyExistException("Organization is already present");
        }
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrg = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrg);
    }

    @Override
    public OrganizationDto findOrganization(String organizationCode) {
        Organization organization=organizationRepository.findByOrganizationCode(organizationCode).orElseThrow(()->
                new ResourceNotFoundException("organization","Id",organizationCode));
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
