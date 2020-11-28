package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

@Setter
@Getter
public class OfferInfo extends BaseInfo {

    private String offerCode;

    @NotEmpty(message = "Offer name can't be empty")
    private String offerName;

    @NotEmpty(message = "Offer Description can't be empty")
    private String offerDescription;

    private String status;
}
