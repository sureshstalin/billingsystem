package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment extends BaseObject {

//    id bigint NOT NULL AUTO_INCREMENT,
//    is_deleted bit(1) DEFAULT NULL,
//    biller_id bigint NOT NULL,
//    product_item_id bigint NOT NULL,
//    date_created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
//    date_modified datetime DEFAULT NULL,
//    KEY payments_fk_biller (biller_id),

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "biller_id")
    Biller biller;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "tax_amount",nullable = false)
    private double taxAmount;

    @Column(name = "product_id")
    private long productId;

}
