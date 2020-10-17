package com.itgarden.mapper;

import com.itgarden.common.Constants;
import com.itgarden.dto.BaseDto;
import com.itgarden.dto.EmployeeDto;
import com.itgarden.dto.UserDto;
import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userDTOtoUser(UserDto userDto);
    @InheritInverseConfiguration
    UserDto userToUserDTO(User user);

}