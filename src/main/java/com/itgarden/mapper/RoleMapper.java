package com.itgarden.mapper;

import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.RoleDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.entity.Employee;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface RoleMapper {

    RoleMapper INSTANCE =  Mappers.getMapper(RoleMapper.class);
    Employee dtoToRole(RoleDTO roleDTO);
    @InheritInverseConfiguration
    RoleDTO roleToDTO(Role role);

}
