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
@Table(name = "tax")
public class Tax extends BaseObject{

    @Column(name = "hsn_code", nullable = false)
    private String hsnCode;

    @Column(name = "tax_percentage", nullable = false)
    private float taxPercentage;

    @Column(name = "tax_description", nullable = false)
    private String taxDescription;
}
