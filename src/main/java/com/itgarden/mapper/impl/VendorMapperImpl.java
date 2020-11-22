package com.itgarden.mapper.impl;

import com.itgarden.dto.VendorInfo;
import com.itgarden.entity.Vendor;
import com.itgarden.mapper.VendorMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-22T16:13:34+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class VendorMapperImpl implements VendorMapper {

    @Override
    public Vendor vendorInfoToVendor(VendorInfo vendorInfo) {
        if ( vendorInfo == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setId( vendorInfo.getId() );
        vendor.setFullName( vendorInfo.getFullName() );
        vendor.setVendorCode( vendorInfo.getVendorCode() );

        return vendor;
    }

    @Override
    public VendorInfo vendorToVendorInfo(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorInfo vendorInfo = new VendorInfo();

        vendorInfo.setId( vendor.getId() );
        vendorInfo.setFullName( vendor.getFullName() );
        vendorInfo.setVendorCode( vendor.getVendorCode() );

        return vendorInfo;
    }
}
