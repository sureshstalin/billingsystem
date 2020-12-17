package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseObject{

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "purchase_order_code",nullable = false)
    private String purchaseOrderCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private Tax tax;

}
