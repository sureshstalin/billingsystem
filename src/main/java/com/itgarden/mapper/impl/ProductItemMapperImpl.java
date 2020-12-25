package com.itgarden.mapper.impl;

import com.itgarden.dto.ProductItemInfo;
import com.itgarden.entity.ProductItem;
import com.itgarden.mapper.ProductItemMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-26T01:29:45+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class ProductItemMapperImpl implements ProductItemMapper {

    @Override
    public ProductItem productItemInfoToProductItem(ProductItemInfo productItemInfo) {
        if ( productItemInfo == null ) {
            return null;
        }

        ProductItem productItem = new ProductItem();

        productItem.setId( productItemInfo.getId() );
        productItem.setProductItemCode( productItemInfo.getProductItemCode() );
        productItem.setStockStatus( productItemInfo.getStockStatus() );

        return productItem;
    }

    @Override
    public ProductItemInfo productItemToProductItemInfo(ProductItem productItem) {
        if ( productItem == null ) {
            return null;
        }

        ProductItemInfo productItemInfo = new ProductItemInfo();

        productItemInfo.setId( productItem.getId() );
        productItemInfo.setProductItemCode( productItem.getProductItemCode() );
        productItemInfo.setStockStatus( productItem.getStockStatus() );

        return productItemInfo;
    }
}
