package com.itgarden.mapper;

import com.itgarden.dto.TaxInfo;
import com.itgarden.dto.UserInfo;
import com.itgarden.entity.Tax;
import com.itgarden.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface TaxMapper {

    TaxMapper INSTANCE =  Mappers.getMapper(TaxMapper.class);
    Tax taxInfoToTax(TaxInfo taxInfo);
    @InheritInverseConfiguration
    TaxInfo taxToTaxInfo(Tax tax);

}
