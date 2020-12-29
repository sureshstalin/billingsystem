package com.itgarden.controller;

import com.itgarden.dto.CategoryInfo;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@RestController
@RequestMapping("api/private/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> save(@Valid @RequestBody CategoryInfo categoryInfo) throws Exception {
        ResponseMessage responseMessage  = categoryService.save(categoryInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<?>> get(@RequestHeader(value="Authorization") String accessToken,@PathVariable Long id) throws Exception {
        ResponseMessage responseMessage = categoryService.findResourceById(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseMessage<?>> getAll(@RequestHeader(value="Authorization") String accessToken) throws Exception {
        ResponseMessage responseMessage = categoryService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
