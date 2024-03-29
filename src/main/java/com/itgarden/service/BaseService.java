package com.itgarden.service;


import com.itgarden.dto.BaseInfo;
import com.itgarden.entity.BaseObject;
import com.itgarden.messages.ResponseMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * Created by Suresh Stalin on 10 / Nov / 2020.
 */

abstract public class BaseService <T>{

    public abstract ResponseMessage findResourceById(Long id) throws Exception;
    public abstract ResponseMessage findResourceByCode(String code) throws Exception;
    public abstract ResponseMessage findAll() throws Exception;
    public abstract ResponseMessage save(T t) throws Exception;

//    public UserDetails getContext() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return userDetails;
//    }
}
