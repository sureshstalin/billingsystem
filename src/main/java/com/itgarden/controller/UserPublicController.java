package com.itgarden.controller;

import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.*;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.bo.RegistrationService;
import com.itgarden.validator.CustomerValidator;
import com.itgarden.validator.EmptyOrNullCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.BindingType;
import javax.xml.ws.RespectBinding;
import java.util.List;

@RestController
@RequestMapping("api/public/users") // http://localhost:9091/api/public/users POST method
@Validated
public class UserPublicController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    CustomerValidator customerValidator;

    @PostMapping("/employees") // http://localhost:9090/api/public/users/employees
    public ResponseEntity<ResponseMessage<?>> updateEmployee(@Valid @RequestBody UserDTO requestBody) throws Exception  {
        requestBody.setType(UserType.EMPLOYEE.name());
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/customers") // http://localhost:9090/api/public/users/customers
    public ResponseEntity<ResponseMessage<?>> updateCustomer(@RequestBody UserDTO requestBody) throws Exception  {
        requestBody.setType(UserType.CUSTOMER.name());
        customerValidator.validate(requestBody);
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/vendors")
    public ResponseEntity<ResponseMessage<?>> updateVendor(@Valid @RequestBody UserDTO requestBody) throws Exception  {
        requestBody.setType(UserType.VENDOR.name());
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }
}
