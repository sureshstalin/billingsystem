package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Getter
@Setter
public class TaxInfo extends BaseInfo {

    @NotEmpty(message = "HSN Code can't be empty")
    private String hsnCode;

    private float taxPercentage;

    @NotEmpty(message = "Tax description can't be empty")
    private String taxDescription;
}
