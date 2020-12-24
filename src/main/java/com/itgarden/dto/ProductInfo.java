package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

@Getter
@Setter
public class ProductInfo extends BaseInfo {

    @NotEmpty(message = "Product name can't be empty")
    private String name;

    @NotEmpty(message = "Product description can't be empty")
    private String description;

    private String productCode;

//    @NotEmpty(message = "Product Price can't be empty")
    @Min(value = 1,message = "Product price can't be less than 0")
    private double price;

    private TaxInfo tax;

    private List<OfferInfo> offers;

    private List<VendorInfo> vendors;

    private CategoryInfo category;

    private Integer stockCount;
}
