package com.itgarden.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/users") // http://localhost:9091/api/public/users POST method
public class UserPublicController {




//    @PostMapping("/employees") // http://localhost:9091/api/public/users/employees
//    public ResponseEntity<ResponseMessage<?>> saveEmployee(@Valid @RequestBody UserDTO requestBody) throws Exception {
//        requestBody.setType(UserType.EMPLOYEE.name());
//        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
//        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/customers") // http://localhost:9091/api/public/users/customers
//    public ResponseEntity<ResponseMessage<?>> saveCustomer(@RequestBody UserDTO requestBody) throws Exception {
//        requestBody.setType(UserType.CUSTOMER.name());
//        customerValidator.validate(requestBody);
//        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
//        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/vendors")
//    public ResponseEntity<ResponseMessage<?>> saveVendor(@Valid @RequestBody UserDTO requestBody) throws Exception {
//        requestBody.setType(UserType.VENDOR.name());
//        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
//        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
//    }


}
