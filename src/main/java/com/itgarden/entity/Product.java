package com.itgarden.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

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
    private double price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private Tax tax;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "offer_id")
//    private List<Offer> offers;

    @ManyToMany(targetEntity = Offer.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="product_offer",
            joinColumns=
            @JoinColumn( name="product_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="offer_id", referencedColumnName="id"))
    private List<Offer> offers;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "vendor_id")
//    private List<Vendor> vendors;

    @ManyToMany(targetEntity = Vendor.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="product_vendor",
            joinColumns=
            @JoinColumn( name="product_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="vendor_id", referencedColumnName="id"))
    private List<Vendor> vendors;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
