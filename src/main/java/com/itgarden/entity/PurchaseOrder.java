package com.itgarden.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}
