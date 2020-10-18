package com.itgarden.mapper.impl;

import com.itgarden.dto.CustomerDTO;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Employee;
import com.itgarden.mapper.CustomerMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T15:59:59+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setFlowType( customer.getFlowType() );
        customerDTO.setFullName( customer.getFullName() );
        customerDTO.setCustomerCode( customer.getCustomerCode() );
        customerDTO.setUser( customer.getUser() );

        return customerDTO;
    }

    @Override
    public Employee dTOToCustomer(CustomerDTO customerVO) {
        if ( customerVO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( customerVO.getId() );
        employee.setFlowType( customerVO.getFlowType() );
        employee.setFullName( customerVO.getFullName() );
        employee.setUser( customerVO.getUser() );

        return employee;
    }
}
