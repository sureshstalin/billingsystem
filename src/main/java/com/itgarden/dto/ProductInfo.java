package com.itgarden.dto;

import com.itgarden.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductInfo extends BaseInfo {

    private String name;

    private String description;

    private String code;

    private double price;

    private Tax tax;

    private List<OfferInfo> offers;

    private List<VendorInfo> vendors;

    private CategoryInfo category;
}
