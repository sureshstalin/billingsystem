package com.itgarden.mapper;

import com.itgarden.dto.ColorInfo;
import com.itgarden.dto.OfferInfo;
import com.itgarden.entity.Color;
import com.itgarden.entity.Offer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface ColorMapper {

    ColorMapper INSTANCE =  Mappers.getMapper(ColorMapper.class);

    Color colorInfoToColor(ColorInfo colorInfo);

    @InheritInverseConfiguration
    ColorInfo colorToColorInfo(Color color);
}
