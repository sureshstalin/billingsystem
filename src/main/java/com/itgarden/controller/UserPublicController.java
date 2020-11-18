package com.itgarden.controller;

import com.itgarden.common.staticdata.TokenType;
import com.itgarden.dto.AuthenticationRequestDTO;
import com.itgarden.dto.AuthenticationResponseDTO;
import com.itgarden.dto.UserDTO;
import com.itgarden.entity.JwtToken;
import com.itgarden.exception.InvalidTokenException;
import com.itgarden.exception.InvalidUserNamePasswordException;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.security.JwtUtils;
import com.itgarden.service.bo.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMessage<AuthenticationResponseDTO>>
        authenticate(@org.jetbrains.annotations.NotNull @RequestBody AuthenticationRequestDTO authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new InvalidUserNamePasswordException(bce.getMessage());
        }
        UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserName());
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.generateAuthResponse(userDetails, TokenType.ACCESS_TOKEN);
        JwtToken jwtTokenResponse = authenticationService.saveJwt(authenticationResponseDTO);
        if (jwtTokenResponse != null) {
            ResponseMessage responseMessage = ResponseMessage.withResponseData(authenticationResponseDTO, "Authentication Success", "Message");
            return new ResponseEntity<ResponseMessage<AuthenticationResponseDTO>>(responseMessage, HttpStatus.OK);
        } else {
            ResponseMessage responseMessage = ResponseMessage.withResponseData(new AuthenticationResponseDTO(), "Authentication Failure", "Error");
            return new ResponseEntity<ResponseMessage<AuthenticationResponseDTO>>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
        String refreshToken = authenticationRequest.getRefreshToken();
        //Validate the token while extracting user.
        if(jwtUtils.isRefreshTokenExpired(refreshToken)) {
            throw  new InvalidTokenException("Invalid Token: The Refresh Token is Expired");
        }
//        jwtUtils.extractUsername(refreshToken,TokenType.REFRESH_TOKEN);
        UserDetails userDetails = authenticationService.loadUserByUsername(jwtUtils.extractUsername(refreshToken,TokenType.REFRESH_TOKEN));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new InvalidUserNamePasswordException(bce.getMessage());
        }
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.generateAuthResponse(userDetails, TokenType.REFRESH_TOKEN);
        JwtToken jwtTokenResponse = authenticationService.saveJwt(authenticationResponseDTO);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(authenticationResponseDTO, "Refresh Token Generated", "Message");
        return new ResponseEntity<ResponseMessage<AuthenticationResponseDTO>>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<?>> login(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<ResponseMessage<?>>
                (ResponseMessage.withResponseData("Success",
                        "User Successfully logged in", "Message"), HttpStatus.OK);
    }
}
