package com.itgarden.mapper;

import com.itgarden.dto.CustomerDTO;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerToDTO(Customer customer);
    @InheritInverseConfiguration
    Employee dTOToCustomer(CustomerDTO customerVO);
}
