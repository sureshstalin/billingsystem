package com.itgarden.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxCalculationResponse {

    private double totalAmount; // unit price * quantity (without Tax)
    private double taxAmount; // Only Tax Amount
    private double grandTotal; // Total amount with Tax
}
