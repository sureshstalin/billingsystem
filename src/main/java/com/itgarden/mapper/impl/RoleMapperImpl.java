package com.itgarden.mapper.impl;

import com.itgarden.dto.RoleDTO;
import com.itgarden.entity.Role;
import com.itgarden.mapper.RoleMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-19T15:17:09+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role roleDTOtoRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setFlowType( roleDTO.getType() );
        role.setName( roleDTO.getName() );
        role.setDescription( roleDTO.getDescription() );

        return role;
    }

    @Override
    public RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setType( role.getFlowType() );
        roleDTO.setName( role.getName() );
        roleDTO.setDescription( role.getDescription() );

        return roleDTO;
    }
}
