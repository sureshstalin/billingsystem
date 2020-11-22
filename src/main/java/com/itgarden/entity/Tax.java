package com.itgarden.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
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
