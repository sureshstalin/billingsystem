package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Biller;
import com.itgarden.entity.ProductItem;
import com.itgarden.entity.Tax;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class PaymentInfo extends BaseObject {

    private BillerInfo biller;

    private ProductItemInfo productItem;

    private Tax tax;

    private double price;

    private double totalPrice;

    private double taxAmount;

    private long productId;

}
