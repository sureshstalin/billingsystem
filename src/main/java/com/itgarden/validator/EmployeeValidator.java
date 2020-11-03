package com.itgarden.validator;

import com.itgarden.dto.AddressDTO;
import com.itgarden.dto.BaseDTO;
import com.itgarden.dto.CustomerDTO;
import com.itgarden.dto.EmployeeDTO;
import com.itgarden.entity.Employee;
import com.itgarden.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeValidator<T> {
//    @Override
//    public void validate(T t) {
//        super.validate(t);
//        if(!getValidationMessages().isEmpty()) {
//            EmployeeDTO employeeDTO = (EmployeeDTO)t;
//            throw  new InvalidInputException(getValidationMessages());
//        }
//    }
}
