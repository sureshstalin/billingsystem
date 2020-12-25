package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequest {

        private List<String> productItemCode; // This is a Barcode
        private String customerMobileNo;
        private int quantity;
}
