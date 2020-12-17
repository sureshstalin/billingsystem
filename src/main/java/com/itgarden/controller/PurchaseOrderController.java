package com.itgarden.controller;

import com.itgarden.common.CodeGenerator;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.dto.PurchaseOrderInfo;
import com.itgarden.entity.PurchaseOrder;
import com.itgarden.mapper.PurchaseOrderMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.bo.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private/product")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("/purchase")
    public ResponseEntity<ResponseMessage<?>> add(@RequestBody PurchaseOrderInfo purchaseOrderInfo) {
        ResponseMessage responseMessage  = purchaseOrderService.save(purchaseOrderInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @PutMapping("/purchase")
    public ResponseEntity<ResponseMessage<?>> update(@RequestBody PurchaseOrderInfo purchaseOrderInfo) {
        ResponseMessage responseMessage  = purchaseOrderService.save(purchaseOrderInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/purchase")
    public ResponseEntity<ResponseMessage<?>> getAll() throws Exception{
        ResponseMessage responseMessage  = purchaseOrderService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<ResponseMessage<?>> get(@PathVariable String id) throws Exception{
        ResponseMessage responseMessage  = purchaseOrderService.findResourceById(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
