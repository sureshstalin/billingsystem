package com.itgarden.mapper;

import com.itgarden.dto.BillerInfo;
import com.itgarden.dto.CategoryInfo;
import com.itgarden.entity.Biller;
import com.itgarden.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface BillerMapper {


    BillerMapper INSTANCE = Mappers.getMapper(BillerMapper.class);

    Biller billerInfoToBiller(BillerInfo billerInfo);

    @InheritInverseConfiguration
    BillerInfo billerToBillerInfo(Biller biller);
}
