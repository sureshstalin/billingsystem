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
@Table(name = "biller")
public class Biller extends BaseObject {

    @Column(name = "bill_no", nullable = false)
    private String billNo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "grand_total", nullable = false)
    private double grandTotal;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_tax_amount", nullable = false)
    private double totalTaxAmount;


}
