package com.itgarden.mapper.impl;

import com.itgarden.dto.BaseDTO;
import com.itgarden.entity.BaseObject;
import com.itgarden.mapper.BaseMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-02T21:19:55+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class BaseMapperImpl implements BaseMapper {

    @Override
    public BaseObject dtoToBaseObject(BaseDTO baseDTO) {
        if ( baseDTO == null ) {
            return null;
        }

        BaseObject baseObject = new BaseObject();

        baseObject.setId( baseDTO.getId() );

        return baseObject;
    }

    @Override
    public BaseDTO baseObjectToDTO(BaseObject baseObject) {
        if ( baseObject == null ) {
            return null;
        }

        BaseDTO baseDTO = new BaseDTO();

        baseDTO.setId( baseObject.getId() );

        return baseDTO;
    }
}
