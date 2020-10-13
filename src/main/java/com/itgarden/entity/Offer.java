package com.itgarden.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "offer")
public class Offer extends BaseObject {

    @Column(name = "offer_code", nullable = false)
    private String offerCode;

    @Column(name = "offer_type", nullable = false)
    private String offerType;

    @Column(name = "offer_name", nullable = false)
    private String offerName;
}
