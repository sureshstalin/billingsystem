package com.itgarden.mapper;

import com.itgarden.dto.OrganizationInfo;
import com.itgarden.dto.UserInfo;
import com.itgarden.entity.Organization;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface OrganizationMapper {

    OrganizationMapper INSTANCE =  Mappers.getMapper(OrganizationMapper.class);
    Organization organizationInfoToOrganization(OrganizationInfo organizationInfo);
    @InheritInverseConfiguration
    OrganizationInfo organizationToOrganizationInfo(Organization organization);
}
