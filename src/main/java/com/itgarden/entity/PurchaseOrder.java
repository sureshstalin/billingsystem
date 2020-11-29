package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseObject{

    @Column(name = "product_code", nullable = false)
    private long productCode;

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "purchase_order_code",nullable = false)
    private String purchaseOrderCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}
