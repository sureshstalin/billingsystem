package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Biller;
import com.itgarden.entity.ProductItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class PaymentInfo extends BaseObject {

    private String billNo;

    private BillerInfo biller;

    private ProductItemInfo productItem;

}
