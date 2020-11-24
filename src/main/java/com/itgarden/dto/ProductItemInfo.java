package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ProductItemInfo extends BaseInfo {

    private String productItemCode;

    private ProductInfo productInfo;

}
