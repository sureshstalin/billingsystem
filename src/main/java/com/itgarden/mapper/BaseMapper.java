package com.itgarden.mapper;

import com.itgarden.dto.BaseDto;
import com.itgarden.dto.EmployeeDto;
import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface BaseMapper {

    BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);
    BaseDto baseObjectToDto(BaseObject baseObject);
    @InheritInverseConfiguration
    BaseObject dToToEmployee(BaseDto baseDto);
}
