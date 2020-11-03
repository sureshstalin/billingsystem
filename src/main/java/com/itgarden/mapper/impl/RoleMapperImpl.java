package com.itgarden.mapper.impl;

import com.itgarden.dto.RoleDTO;
import com.itgarden.entity.Employee;
import com.itgarden.entity.Role;
import com.itgarden.mapper.RoleMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T21:19:55+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Employee dtoToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( roleDTO.getId() );

        return employee;
    }

    @Override
    public RoleDTO roleToDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setDescription( role.getDescription() );

        return roleDTO;
    }
}
