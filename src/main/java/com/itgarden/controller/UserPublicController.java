package com.itgarden.controller;

import com.itgarden.dto.*;
import com.itgarden.entity.Employee;
import com.itgarden.entity.User;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.UserMapper;
import com.itgarden.service.BillingBaseService;
import com.itgarden.service.bo.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/users")
public class UserPublicController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ResponseMessage<EmployeeDto>> saveUser(@RequestBody UserDto requestBody)  {
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<EmployeeDto>>(responseMessage, HttpStatus.CREATED);
    }


}
