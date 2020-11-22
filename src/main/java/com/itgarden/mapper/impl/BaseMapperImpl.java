package com.itgarden.mapper.impl;

import com.itgarden.dto.BaseInfo;
import com.itgarden.entity.BaseObject;
import com.itgarden.mapper.BaseMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-22T16:13:34+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class BaseMapperImpl implements BaseMapper {

    @Override
    public BaseObject baseInfoToBaseObject(BaseInfo baseInfo) {
        if ( baseInfo == null ) {
            return null;
        }

        BaseObject baseObject = new BaseObject();

        baseObject.setId( baseInfo.getId() );

        return baseObject;
    }

    @Override
    public BaseInfo baseObjectToBaseInfo(BaseObject baseObject) {
        if ( baseObject == null ) {
            return null;
        }

        BaseInfo baseInfo = new BaseInfo();

        baseInfo.setId( baseObject.getId() );

        return baseInfo;
    }
}
