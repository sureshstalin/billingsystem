package com.itgarden.controller;

import com.itgarden.common.staticdata.BillStatus;
import com.itgarden.dto.*;
import com.itgarden.entity.Biller;
import com.itgarden.mapper.BillerMapper;
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

    @PostMapping() // api/private/bills
    public ResponseEntity<ResponseMessage<?>> doPayment(@RequestBody PaymentRequest paymentRequest) {
        String mobileNo = paymentRequest.getCustomerMobileNo();
        List<String> productItemCode = paymentRequest.getProductItemCode();
        ResponseMessage responseMessage = billerService.save(paymentRequest);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping() // api/private/bills
    public ResponseEntity<ResponseMessage<?>> getAllBills(@RequestHeader(value="Authorization") String accessToken) throws Exception {
        ResponseMessage responseMessage = billerService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }
    @GetMapping("/{billNo}") // api/private/bills/10000
    public ResponseEntity<ResponseMessage<?>> getBill(@PathVariable String billNo) throws Exception {
        ResponseMessage responseMessage = billerService.findResourceByCode(billNo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{billNo}/payments") // api/private/bills/10000/payments
    public ResponseEntity<ResponseMessage<?>> getAllPaymentsFromBill(@PathVariable String billNo) throws Exception {
        ResponseMessage responseMessage = billerService.findResourceByCode(billNo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("{billNo}/payments/{paymentId}") // api/private/bills/10000/payments/2
    public ResponseEntity<ResponseMessage<?>> getPaymentByPaymentId(@PathVariable String billNo,@PathVariable Long paymentId) throws Exception {
        ResponseMessage responseMessage = billerService.findPaymentById(paymentId);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/{billNo}")
    public ResponseEntity<ResponseMessage<?>> cancelBill(@PathVariable String billNo) throws Exception {
        Biller biller = billerService.findBillByBillNo(billNo);
        biller.setBillStatus(BillStatus.REFUND.name());
        billerService.updateBiller(biller);
        BillerInfo billerInfo = BillerMapper.INSTANCE.billerToBillerInfo(biller);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(billerInfo, "", "");
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/{billNo}/payments/{paymentId}")
    public ResponseEntity<ResponseMessage<?>> cancelPayment(@PathVariable Long paymentId) {
        ResponseMessage responseMessage = billerService.cancelPayment(paymentId);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }


}
