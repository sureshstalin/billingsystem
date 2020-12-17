package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Product;
import com.itgarden.entity.Tax;
import com.itgarden.entity.Vendor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
public class PurchaseOrderInfo extends BaseObject {

    private String productName;

    private String productDescription;

    private int quantity;

    private double price;

    private double unitPrice;

    private String purchaseOrderCode;

    private VendorInfo vendor;

    private TaxInfo tax;

}
