package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer extends BaseObject {

    @Column(name = "offer_code", nullable = false)
    private String offerCode;

    @Column(name = "offer_name", nullable = false)
    private String offerName;

    @Column(name = "offer_description", nullable = false)
    private String offerDescription;

    @Column(name = "status", nullable = false)
    private String status;
}
