package com.itgarden.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TaxCalculation {

    private double unitPrice = 0d;
    private float taxPercentage = 0f;
    private double totalAmount = 0d;
    private double price = 0d;
    private double taxAmount = 0d;
    private int quantity;


    public TaxCalculationResponse calculateTax(TaxCalculationInput calculationInput) {
        TaxCalculationResponse taxCalculationResponse = new TaxCalculationResponse();
        double unitPrice = calculationInput.getUnitPrice();
        float taxPercentage = calculationInput.getTaxPercentage();
        int quantity = calculationInput.getQuantity();
        double taxAmount = 0d;
        taxCalculationResponse.setTotalAmount(unitPrice * quantity);
        if (taxPercentage > 0) {
            taxAmount = (unitPrice * taxPercentage) / 100; // Tqx amount
            taxCalculationResponse.setTaxAmount(quantity * taxAmount);
            // getPrice return Unit Price with Tax and multiplying with quantity which is tatal amount
            taxCalculationResponse.setTotalAmount(quantity * unitPrice);
            taxCalculationResponse.setGrandTotal(taxCalculationResponse.getTotalAmount() + taxCalculationResponse.getTaxAmount());
        }
        return taxCalculationResponse;
    }
}
