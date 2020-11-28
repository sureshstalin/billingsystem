package com.itgarden.controller;

import com.itgarden.common.staticdata.TokenType;
import com.itgarden.common.staticdata.UserType;
import com.itgarden.dto.AuthenticationRequestInfo;
import com.itgarden.dto.AuthenticationResponseInfo;
import com.itgarden.dto.EmployeeInfo;
import com.itgarden.dto.UserRoleInfo;
import com.itgarden.entity.JwtToken;
import com.itgarden.exception.DuplicateKeyFoundException;
import com.itgarden.exception.InvalidTokenException;
import com.itgarden.exception.InvalidUserNamePasswordException;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.security.JwtUtils;
import com.itgarden.service.bo.AuthenticationService;
import com.itgarden.service.bo.RegistrationService;
import com.itgarden.service.bo.RoleService;
import com.itgarden.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * Created by Suresh Stalin on 17 / Nov / 2020.
 */

@RestController
@RequestMapping("api/public") // http://localhost:9091/api/public/users POST method
public class UserPublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleService roleService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMessage<AuthenticationResponseInfo>>
    authenticate(@RequestBody AuthenticationRequestInfo authenticationRequest) {
        try {
            // This authenticate method call AuthenticationService.loadUserByUsername
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new InvalidUserNamePasswordException(bce.getMessage());
        }
        UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserName());
        AuthenticationResponseInfo authenticationResponseDTO = authenticationService.generateAuthResponse(userDetails, TokenType.ACCESS_TOKEN);
        JwtToken jwtTokenResponse = authenticationService.saveJwt(authenticationResponseDTO);
        if (jwtTokenResponse != null) {
            ResponseMessage responseMessage = ResponseMessage.withResponseData(authenticationResponseDTO, "Authentication Success", "Message");
            return new ResponseEntity<ResponseMessage<AuthenticationResponseInfo>>(responseMessage, HttpStatus.OK);
        } else {
            ResponseMessage responseMessage = ResponseMessage.withResponseData(new AuthenticationResponseInfo(), "Authentication Failure", "Error");
            return new ResponseEntity<ResponseMessage<AuthenticationResponseInfo>>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody AuthenticationRequestInfo authenticationRequest) throws Exception {
        String refreshToken = authenticationRequest.getRefreshToken();
        //Validate the token while extracting user.
        if (jwtUtils.isRefreshTokenExpired(refreshToken)) {
            throw new InvalidTokenException("Invalid Token: The Refresh Token is Expired");
        }
//        jwtUtils.extractUsername(refreshToken,TokenType.REFRESH_TOKEN);
        UserDetails userDetails = authenticationService.loadUserByUsername(jwtUtils.extractUsername(refreshToken, TokenType.REFRESH_TOKEN));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new InvalidUserNamePasswordException(bce.getMessage());
        }
        AuthenticationResponseInfo authenticationResponseDTO = authenticationService.generateAuthResponse(userDetails, TokenType.REFRESH_TOKEN);
        JwtToken jwtTokenResponse = authenticationService.saveJwt(authenticationResponseDTO);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(authenticationResponseDTO, "Refresh Token Generated", "Message");
        return new ResponseEntity<ResponseMessage<AuthenticationResponseInfo>>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/superadmin/{id}") // http://localhost:9091/api/public/users/employees
    public ResponseEntity<ResponseMessage<?>> saveEmployee(@Valid @RequestBody EmployeeInfo requestBody, @PathVariable String id) throws Exception {
        requestBody.setType(UserType.EMPLOYEE.name());
        ResponseMessage responseMessage = roleService.findResourceById(id);
        UserRoleInfo userRoleInfo = (UserRoleInfo) responseMessage.getResponseClassType();
        if (userRoleInfo.getRoleId() == Long.parseLong(id)) {
            throw new DuplicateKeyFoundException("The Super Admin already created: It can create only once.");
        } else {
            userValidator.validate(requestBody);
            ResponseMessage registrationResponse = registrationService.createSuperAdmin(requestBody);
            return new ResponseEntity<ResponseMessage<?>>(registrationResponse, HttpStatus.CREATED);
        }

    }

}
