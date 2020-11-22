package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class OfferInfo extends BaseInfo {

    private String offerCode;

    private String offerType;

    private String offerName;
}
