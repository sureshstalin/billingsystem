package com.itgarden.mapper;

import com.itgarden.dto.RoleDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role roleDTOtoRole(RoleDTO roleDTO);
    @InheritInverseConfiguration
    RoleDTO roleToRoleDTO(Role role);

}
