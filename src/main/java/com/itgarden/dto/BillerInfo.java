package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
public class BillerInfo extends BaseInfo {

    private String billNo;

    private CustomerInfo customer;

    private double grandTotal;

    private int quantity;

    private double totalTaxAmount;

}
