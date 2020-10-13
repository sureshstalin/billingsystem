package com.itgarden.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tax")
public class Tax extends BaseObject{

    @Column(name = "hsn_code", nullable = false)
    private String hsnCode;

    @Column(name = "tax_percentage", nullable = false)
    private String taxPercentage;

    @Column(name = "tax_description", nullable = false)
    private String taxDescription;
}
