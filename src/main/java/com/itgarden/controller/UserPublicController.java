package com.itgarden.controller;

import com.itgarden.dto.*;
import com.itgarden.service.bo.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/users") // http://localhost:9091/api/public/users POST method
public class UserPublicController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> saveUser(@RequestBody UserDTO requestBody)  {
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

}
