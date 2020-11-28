package com.itgarden.mapper;

import com.itgarden.dto.ProductItemInfo;
import com.itgarden.dto.UserInfo;
import com.itgarden.entity.ProductItem;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface ProductItemMapper {

    ProductItemMapper INSTANCE =  Mappers.getMapper(ProductItemMapper.class);
    ProductItem productItemInfoToProductItem(ProductItemInfo productItemInfo);
    @InheritInverseConfiguration
    ProductItemInfo productItemToProductItemInfo(ProductItem productItem);

}
