package com.itgarden.dto;

import com.itgarden.entity.*;

import java.util.List;

public class ProductInfo extends BaseInfo {

    private String name;

    private String description;

    private String code;

    private double price;

    private Tax tax;

    private List<OfferInfo> offers;

    private List<VendorInfo> vendors;

    private Category category;
}
