package com.itgarden.controller;

import com.itgarden.dto.*;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.BillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/private/bills")
public class BillerController {

        @Autowired
        private BillerService billerService;

        @PostMapping("/payments")
        public ResponseEntity<ResponseMessage<?>> doPayment(@RequestBody PaymentRequest paymentRequest) {
            String mobileNo = paymentRequest.getCustomerMobileNo();
            List<String> productItemCode = paymentRequest.getProductItemCode();
            ResponseMessage responseMessage = billerService.save(paymentRequest);
            return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
        }

    @PutMapping("/payments/cancel")
    public ResponseEntity<ResponseMessage<?>> doCancelPayment(@RequestBody PaymentRequest paymentRequest) {
        List<String> productItemCode = paymentRequest.getProductItemCode();
        List<PaymentInfo> paymentInfos = billerService.cancel(paymentRequest);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(paymentInfos,"","");
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/payments/{billNo}")
    public ResponseEntity<ResponseMessage<?>> getBill(@PathVariable String billNo) throws Exception {
        ResponseMessage responseMessage = billerService.findResourceByCode(billNo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
