package com.itgarden.mapper;

import com.itgarden.common.Constants;
import com.itgarden.dto.EmployeeDto;
import com.itgarden.dto.UserDto;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDto employeeToDTO(Employee employee);
    @InheritInverseConfiguration
    Employee dToToEmployee(EmployeeDto employeeDto);

//    @AfterMapping
//    default void afterMapping(@MappingTarget Employee employee, EmployeeDto employeeDto) {
//        employeeDto.setId(employee.getId());
//        employeeDto.setFlowType(Constants.EMPLOYEE_FLOW_TYPE);
//    }
}
