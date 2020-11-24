package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaxInfo extends BaseInfo {

    @NotEmpty(message = "HSN Code can't be empty")
    private String hsnCode;

    @NotEmpty(message = "Percentage can't be empty")
    private String taxPercentage;

    @NotEmpty(message = "Tax description can't be empty")
    private String taxDescription;
}
