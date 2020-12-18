package com.itgarden.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxCalculationInput {

    private double unitPrice;
    private float taxPercentage;
    private int quantity;
}
