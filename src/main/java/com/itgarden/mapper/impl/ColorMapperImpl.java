package com.itgarden.mapper.impl;

import com.itgarden.dto.ColorInfo;
import com.itgarden.entity.Color;
import com.itgarden.mapper.ColorMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-08T18:25:47+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class ColorMapperImpl implements ColorMapper {

    @Override
    public Color colorInfoToColor(ColorInfo colorInfo) {
        if ( colorInfo == null ) {
            return null;
        }

        Color color = new Color();

        color.setColorId( colorInfo.getColorId() );
        color.setColorName( colorInfo.getColorName() );

        return color;
    }

    @Override
    public ColorInfo colorToColorInfo(Color color) {
        if ( color == null ) {
            return null;
        }

        ColorInfo colorInfo = new ColorInfo();

        colorInfo.setColorId( color.getColorId() );
        colorInfo.setColorName( color.getColorName() );

        return colorInfo;
    }
}
