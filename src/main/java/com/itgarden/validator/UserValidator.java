package com.itgarden.validator;

import com.itgarden.dto.*;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Vendor;
import com.itgarden.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserValidator<T> {

    public void validate(T t) {
        List<String> validationMessages = new ArrayList<>();
        if (t instanceof EmployeeDTO) {
            EmployeeDTO employeeDTO = (EmployeeDTO) t;
            UserDTO userDTO = employeeDTO.getUser();
            validationMessages = validateUser(userDTO);
            if (StringUtils.isEmpty(employeeDTO.getFullName())) {
                validationMessages.add("Full Name can't be empty");
            }
        }

        if (t instanceof Vendor) {
            VendorDTO vendorDTO = (VendorDTO) t;
            UserDTO userDTO = vendorDTO.getUser();
            validationMessages = validateUser(userDTO);
            if (StringUtils.isEmpty(vendorDTO.getFullName())) {
                validationMessages.add("Full Name can't be empty");
            }
        }

        if (t instanceof Customer) {
            CustomerDTO customerDTO = (CustomerDTO) t;
            UserDTO userDTO = customerDTO.getUser();
            if (StringUtils.isEmpty(userDTO.getMobileNo())) {
                validationMessages.add("Mobile No can't be empty");
            }
        }
        if (!validationMessages.isEmpty()) {
            throw new InvalidInputException(validationMessages);
        }
    }


    public List<String> validateUser(UserDTO userDTO) {
        List<String> validationMessages = new ArrayList<>();
        if (StringUtils.isEmpty(userDTO.getMobileNo())) {
            validationMessages.add("Mobile No can't be empty");
        }
        if (StringUtils.isEmpty(userDTO.getFirstName())) {
            validationMessages.add("First name can't be empty");
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
        AddressDTO addressDTO = userDTO.getAddressList().get(0);
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
