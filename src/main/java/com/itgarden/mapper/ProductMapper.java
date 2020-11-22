package com.itgarden.mapper;

import com.itgarden.dto.CustomerInfo;
import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface ProductMapper {

    ProductMapper INSTANCE =  Mappers.getMapper(ProductMapper.class);
    Product productInfoToProduct(ProductInfo productInfo);
    @InheritInverseConfiguration
    ProductInfo productToProductInfo(Product product);
}
