package com.itgarden.controller;

import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.CustomerDTO;
import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.dto.VendorDTO;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.bo.CustomerService;
import com.itgarden.service.bo.EmployeeService;
import com.itgarden.service.bo.RegistrationService;
import com.itgarden.service.bo.VendorService;
import com.itgarden.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/private/users")
@Validated
public class UserPrivateController {

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final VendorService vendorService;
    private final RegistrationService registrationService;
    private final UserValidator userValidator;
//    private final CustomerValidator customerValidator;

    @Autowired
    public UserPrivateController(CustomerService customerService,
                                 EmployeeService employeeService,
                                 VendorService vendorService,
                                 RegistrationService registrationService,
                                 UserValidator userValidator) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.vendorService = vendorService;
        this.registrationService = registrationService;
        this.userValidator  = userValidator;
//        this.customerValidator = customerValidator;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ResponseMessage<?>> viewCustomer(@PathVariable String id) throws Exception {
        ResponseMessage responseMessage = customerService.findCustomer(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseMessage<?>> viewEmployee(@PathVariable String id) throws Exception {
        ResponseMessage responseMessage = employeeService.findEmployee(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/vendors/{id}")
    public ResponseEntity<ResponseMessage<?>> viewVendor(@PathVariable String id) throws Exception {
            ResponseMessage responseMessage = vendorService.findVendor(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage<?>> login(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<ResponseMessage<?>>
                (ResponseMessage.withResponseData("Success",
                        "User Successfully logged in", "Message"), HttpStatus.OK);
    }


    @PostMapping("/employees") // http://localhost:9091/api/public/users/employees
    public ResponseEntity<ResponseMessage<?>> saveEmployee(@Valid @RequestBody EmployeeDTO requestBody) throws Exception {
        requestBody.setType(UserType.EMPLOYEE.name());
        userValidator.validate(requestBody);
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/customers") // http://localhost:9091/api/public/users/customers
    public ResponseEntity<ResponseMessage<?>> saveCustomer(@Valid @RequestBody CustomerDTO requestBody) throws Exception {
        requestBody.setType(UserType.CUSTOMER.name());
        userValidator.validate(requestBody);
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/vendors")
    public ResponseEntity<ResponseMessage<?>> saveVendor(@Valid @RequestBody VendorDTO requestBody) throws Exception {
        requestBody.setType(UserType.VENDOR.name());
        userValidator.validate(requestBody);
        ResponseMessage responseMessage = registrationService.doRegistration(requestBody);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }
}
