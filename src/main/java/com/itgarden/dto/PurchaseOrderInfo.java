package com.itgarden.dto;

import com.itgarden.common.staticdata.PurchaseOrderStatus;
import com.itgarden.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
public class PurchaseOrderInfo extends BaseObject {

    @NotEmpty(message = "Product Name can't be empty")
    private String productName;

    @NotEmpty(message = "Product description can't be empty")
    private String productDescription;

    @Min(value = 1)
    private int quantity;

    @Min(value = 1)
    private double unitPrice;

    private String purchaseOrderCode;

    private VendorInfo vendor;

    private TaxInfo tax;

    private double taxAmount;

    private double totalAmount;

    private CategoryInfo category;

    private double grandTotal;

    private PurchaseOrderStatus purchaseOrderStatus;

}
