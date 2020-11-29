package com.itgarden.controller;

import com.itgarden.dto.UserInfo;
import com.itgarden.entity.User;
import com.itgarden.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * Created by Suresh Stalin on 17 / Oct / 2020.
 */

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
    public ResponseEntity<Void> mapperTesting(@RequestBody UserInfo userDto)  {
        User user = UserMapper.INSTANCE.userInfoToUser(userDto);
        log.debug("Entity Class......");
        log.info("Entity First Name " + user.getFirstName());
        log.info("Entity Last Name " + user.getLastName());
        UserInfo userDto2 = UserMapper.INSTANCE.userToUserInfo(user);
        log.debug("DTO Class......");
        log.info("DTO First Name " + userDto2.getFirstName());
        log.info("DTO Last Name " + userDto2.getLastName());
        log.error("This is error info......");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("Server is running...", HttpStatus.OK);
    }
}


