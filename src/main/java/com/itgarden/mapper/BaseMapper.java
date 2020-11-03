package com.itgarden.mapper;

import com.itgarden.dto.BaseDTO;
import com.itgarden.dto.CustomerDTO;
import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface BaseMapper {

    BaseMapper INSTANCE =  Mappers.getMapper(BaseMapper.class);
    BaseObject dtoToBaseObject(BaseDTO baseDTO);
    @InheritInverseConfiguration
    BaseDTO baseObjectToDTO(BaseObject baseObject);
}
