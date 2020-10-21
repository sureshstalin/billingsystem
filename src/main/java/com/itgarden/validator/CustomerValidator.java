package com.itgarden.validator;

import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.UserDTO;
import com.itgarden.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerValidator {

    private List<String> validationMessages;
    public void validate(UserDTO userDTO) {
        validationMessages = new ArrayList<>();
        if(userDTO != null && userDTO.getType().equalsIgnoreCase(UserType.CUSTOMER.name())) {
            if(StringUtils.isEmpty(userDTO.getMobileNo())) {
                validationMessages.add("Mobile No can't be empty");
            }
        }
        if(!validationMessages.isEmpty()) {
            throw new InvalidInputException(validationMessages);
        }
    }
}
