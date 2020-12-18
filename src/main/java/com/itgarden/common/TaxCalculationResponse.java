package com.itgarden.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxCalculationResponse {

    private double totalAmount;
    private double taxAmount;
    private double grandTotal;
}
