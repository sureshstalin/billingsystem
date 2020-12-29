package com.itgarden.dto;

import com.itgarden.entity.ProductItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {

    private List<ProductItemInfo> productItemList;
    private ProductInfo productInfo;

}
