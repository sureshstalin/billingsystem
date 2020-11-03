package com.itgarden.controller;

import com.itgarden.dto.UserDTO;
import com.itgarden.entity.User;
import com.itgarden.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("api/public/test")
public class TestController {

    @GetMapping("/log")
    public ResponseEntity<Void> logTest()  {
        log.info("This is test log1");
        log.info("This is test log2");
        log.info("This is test log3");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/mapper")
    public ResponseEntity<Void> mapperTesting(@RequestBody UserDTO userDto)  {
        User user = UserMapper.INSTANCE.dtoToUser(userDto);
        log.debug("Entity Class......");
        log.info("Entity First Name " + user.getFirstName());
        log.info("Entity Last Name " + user.getLastName());
        UserDTO userDto2 = UserMapper.INSTANCE.userToDTO(user);
        log.debug("DTO Class......");
        log.info("DTO First Name " + userDto2.getFirstName());
        log.info("DTO Last Name " + userDto2.getLastName());
        log.error("This is error info......");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}


