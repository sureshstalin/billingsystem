package com.itgarden.mapper;

import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface EmployeeMapper {


    EmployeeMapper INSTANCE =  Mappers.getMapper(EmployeeMapper.class);
    Employee dtoToEmployee(EmployeeDTO employeeDTO);
    @InheritInverseConfiguration
    EmployeeDTO employeeToDTO(Employee employee);

//    @AfterMapping
//    default void afterMapping(@MappingTarget Employee employee, EmployeeDto employeeDto) {
//        employeeDto.setId(employee.getId());
//        employeeDto.setFlowType(Constants.EMPLOYEE_FLOW_TYPE);
//    }
}
