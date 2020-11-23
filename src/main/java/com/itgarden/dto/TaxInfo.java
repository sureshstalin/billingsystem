package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
public class TaxInfo extends BaseInfo {

    private String hsnCode;

    private String taxPercentage;

    private String taxDescription;
}
