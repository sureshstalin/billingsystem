package com.itgarden.validator;

import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.entity.Employee;
import com.itgarden.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class EmptyORNullCheckValidator implements
        ConstraintValidator<EmptyOrNullCheck, EmployeeDTO> {

    @Autowired
    Validator validator;

    @Override
    public void initialize(EmptyOrNullCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(EmployeeDTO employeeDTO, ConstraintValidatorContext constraintValidatorContext)  {
        boolean isValid = true;
        if(StringUtils.isEmpty(employeeDTO.getUser().getFirstName())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("First Name can't be empty")
                    .addConstraintViolation();
            // return false if validation fails
            isValid = false;
        }
        return isValid;
    }
}
