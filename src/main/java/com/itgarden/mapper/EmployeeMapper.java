package com.itgarden.mapper;

import com.itgarden.dto.EmployeeDTO;
import com.itgarden.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO employeeToDTO(Employee employee);
    @InheritInverseConfiguration
    Employee dToToEmployee(EmployeeDTO employeeDto);

//    @AfterMapping
//    default void afterMapping(@MappingTarget Employee employee, EmployeeDto employeeDto) {
//        employeeDto.setId(employee.getId());
//        employeeDto.setFlowType(Constants.EMPLOYEE_FLOW_TYPE);
//    }
}
