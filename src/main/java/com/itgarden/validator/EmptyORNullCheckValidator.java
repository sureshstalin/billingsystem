package com.itgarden.validator;

import com.itgarden.dto.UserDTO;
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
public class EmptyORNullCheckValidator implements ConstraintValidator<EmptyOrNullCheck,UserDTO> {

    @Autowired
    Validator validator;

    @Override
    public void initialize(EmptyOrNullCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext)  {
        boolean isValid = true;
        if(StringUtils.isEmpty(userDTO.getMobileNo())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Enter valid mobile no.")
                    .addConstraintViolation();
            // return false if validation fails
            isValid = false;
        }
        return isValid;
    }
}
