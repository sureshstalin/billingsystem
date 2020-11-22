package com.itgarden.controller;

import com.itgarden.entity.Product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private")
public class ProductManagementController {


    public void saveProduct(@RequestBody Product dp) {

    }

}
