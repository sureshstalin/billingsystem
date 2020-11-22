package com.itgarden.controller;

import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.Product;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.bo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/product")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> saveProduct(@RequestBody ProductInfo productInfo) {
       ProductInfo productInfoResponse =  productService.save(productInfo);
       ResponseMessage responseMessage = ResponseMessage.withResponseData(productInfoResponse,"Product updated Successfully",
                "message");
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
