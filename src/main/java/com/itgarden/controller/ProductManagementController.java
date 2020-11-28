package com.itgarden.controller;

import com.itgarden.dto.ProductInfo;
import com.itgarden.dto.ProductItemInfo;
import com.itgarden.entity.Product;
import com.itgarden.entity.ProductItem;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.bo.ProductItemService;
import com.itgarden.service.bo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

@RestController
@RequestMapping("api/private/products")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductItemService productItemService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> saveProduct(@RequestBody ProductInfo productInfo) {
        ResponseMessage responseMessage =  productService.save(productInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<?>> get(@PathVariable String id) throws Exception {
        ResponseMessage responseMessage = productService.findResourceById(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseMessage<?>> getAll() throws Exception {
        ResponseMessage responseMessage = productService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

//    @PostMapping("/items")
//    public ResponseEntity<ResponseMessage<?>> saveProductItems(@RequestBody ProductItemInfo productItemInfo) {
//        ResponseMessage responseMessage =  productItemService.save(productItemInfo);
//        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
//    }
    @PostMapping("/items/{id}")
    public ResponseEntity<ResponseMessage<?>> getProductItem(@PathVariable String id) throws Exception {
        ResponseMessage responseMessage =  productItemService.findResourceById(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<ResponseMessage<?>> getAllProductItems() throws Exception {
        ResponseMessage responseMessage =  productItemService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
