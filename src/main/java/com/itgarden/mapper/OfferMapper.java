package com.itgarden.mapper;

import com.itgarden.dto.OfferInfo;
import com.itgarden.dto.ProductInfo;
import com.itgarden.entity.Offer;
import com.itgarden.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface OfferMapper {

    OfferMapper INSTANCE =  Mappers.getMapper(OfferMapper.class);
    Offer offerInfoToOffer(OfferInfo offerInfo);
    @InheritInverseConfiguration
    OfferInfo offerToOfferInfo(Offer offer);
}
