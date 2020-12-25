package com.itgarden.validator;

import com.itgarden.dto.*;
import com.itgarden.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 02 / Nov / 2020.
 */

@Component
public class UserValidator<T> {

    public void validate(T t) {
        List<String> validationMessages = new ArrayList<>();
        if (t instanceof EmployeeInfo) {
            EmployeeInfo employeeDTO = (EmployeeInfo) t;
            UserInfo userDTO = employeeDTO.getUser();
            validationMessages = validateUser(userDTO);
            if (StringUtils.isEmpty(employeeDTO.getFullName())) {
                validationMessages.add("Full Name can't be empty");
            }
        }

        if (t instanceof VendorInfo) {
            VendorInfo vendorInfo = (VendorInfo) t;
            UserInfo userDTO = vendorInfo.getUser();
            validationMessages = validateUser(userDTO);
            if (StringUtils.isEmpty(vendorInfo.getFullName())) {
                validationMessages.add("Full Name can't be empty");
            }
        }

        if (t instanceof CustomerInfo) {
            CustomerInfo customerDTO = (CustomerInfo) t;
            UserInfo userDTO = customerDTO.getUser();
            if (StringUtils.isEmpty(userDTO.getMobileNo())) {
                validationMessages.add("Mobile No can't be empty");
            }
//            if (userDTO != null && userDTO.getId() == null) {
//                if (StringUtils.isEmpty(userDTO.getPassword())) {
//                    validationMessages.add("Password can't be empty");
//                }
//                if (StringUtils.isEmpty(userDTO.getRePassword())) {
//                    validationMessages.add("Re enter Password can't be empty");
//                }
//            }
        }
        if (!validationMessages.isEmpty()) {
            throw new InvalidInputException(validationMessages);
        }
    }


    private List<String> validateUser(UserInfo userDTO) {
        List<String> validationMessages = new ArrayList<>();
        if (StringUtils.isEmpty(userDTO.getMobileNo())) {
            validationMessages.add("Mobile No can't be empty");
        }
        if (StringUtils.isEmpty(userDTO.getFirstName())) {
            validationMessages.add("First name can't be empty");
        }
        if (StringUtils.isEmpty(userDTO.getEmailId())) {
            validationMessages.add("Email can't be empty");
        }
        if (StringUtils.isEmpty(userDTO.getLastName())) {
            validationMessages.add("Last Name can't be empty");
        }
        if (userDTO != null && userDTO.getId() == null) {
            if (StringUtils.isEmpty(userDTO.getPassword())) {
                validationMessages.add("Password can't be empty");
            }
            if (StringUtils.isEmpty(userDTO.getRePassword())) {
                validationMessages.add("Re enter Password can't be empty");
            }
        }
        AddressInfo addressDTO = userDTO.getAddressList().get(0);
        if (StringUtils.isEmpty(addressDTO.getAddress1())) {
            validationMessages.add("Address1 can't be empty");
        }
        if (StringUtils.isEmpty(addressDTO.getCity())) {
            validationMessages.add("City can't be empty");
        }
        if (StringUtils.isEmpty(addressDTO.getState())) {
            validationMessages.add("State can't be empty");
        }
        if (StringUtils.isEmpty(addressDTO.getCountry())) {
            validationMessages.add("Country can't be empty");
        }
        return validationMessages;
    }
}
