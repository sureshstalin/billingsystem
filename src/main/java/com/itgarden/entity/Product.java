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
@Table(name = "product")
public class Product extends BaseObject{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock_count", nullable = false )
    private int stockCount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @ManyToMany(targetEntity = Offer.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="product_offer",
            joinColumns=
            @JoinColumn( name="product_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="offer_id", referencedColumnName="id"))
    private List<Offer> offers;

    @ManyToMany(targetEntity = Vendor.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="product_vendor",
            joinColumns=
            @JoinColumn( name="product_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="vendor_id", referencedColumnName="id"))
    private List<Vendor> vendors;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
