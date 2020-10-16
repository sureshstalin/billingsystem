package com.itgarden.mapper;

import com.itgarden.dto.EmployeeDto;
import com.itgarden.dto.UserDto;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDto employeeToDTO(Employee employee);
    @InheritInverseConfiguration
    Employee dToToEmployee(EmployeeDto employeeDto);
}
