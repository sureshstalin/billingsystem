package com.itgarden.mapper.impl;

import com.itgarden.dto.OfferInfo;
import com.itgarden.dto.ProductInfo;
import com.itgarden.dto.VendorInfo;
import com.itgarden.entity.Offer;
import com.itgarden.entity.Product;
import com.itgarden.entity.Vendor;
import com.itgarden.mapper.ProductMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-22T17:13:54+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productInfoToProduct(ProductInfo productInfo) {
        if ( productInfo == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productInfo.getId() );
        product.setName( productInfo.getName() );
        product.setDescription( productInfo.getDescription() );
        product.setPrice( productInfo.getPrice() );
        product.setTax( productInfo.getTax() );
        product.setOffers( offerInfoListToOfferList( productInfo.getOffers() ) );
        product.setVendors( vendorInfoListToVendorList( productInfo.getVendors() ) );
        product.setCategory( productInfo.getCategory() );

        return product;
    }

    @Override
    public ProductInfo productToProductInfo(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setId( product.getId() );
        productInfo.setName( product.getName() );
        productInfo.setDescription( product.getDescription() );
        productInfo.setPrice( product.getPrice() );
        productInfo.setTax( product.getTax() );
        productInfo.setOffers( offerListToOfferInfoList( product.getOffers() ) );
        productInfo.setVendors( vendorListToVendorInfoList( product.getVendors() ) );
        productInfo.setCategory( product.getCategory() );

        return productInfo;
    }

    protected Offer offerInfoToOffer(OfferInfo offerInfo) {
        if ( offerInfo == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setId( offerInfo.getId() );
        offer.setOfferCode( offerInfo.getOfferCode() );
        offer.setOfferType( offerInfo.getOfferType() );
        offer.setOfferName( offerInfo.getOfferName() );

        return offer;
    }

    protected List<Offer> offerInfoListToOfferList(List<OfferInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Offer> list1 = new ArrayList<Offer>( list.size() );
        for ( OfferInfo offerInfo : list ) {
            list1.add( offerInfoToOffer( offerInfo ) );
        }

        return list1;
    }

    protected Vendor vendorInfoToVendor(VendorInfo vendorInfo) {
        if ( vendorInfo == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setId( vendorInfo.getId() );
        vendor.setFullName( vendorInfo.getFullName() );
        vendor.setVendorCode( vendorInfo.getVendorCode() );

        return vendor;
    }

    protected List<Vendor> vendorInfoListToVendorList(List<VendorInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Vendor> list1 = new ArrayList<Vendor>( list.size() );
        for ( VendorInfo vendorInfo : list ) {
            list1.add( vendorInfoToVendor( vendorInfo ) );
        }

        return list1;
    }

    protected OfferInfo offerToOfferInfo(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferInfo offerInfo = new OfferInfo();

        offerInfo.setId( offer.getId() );
        offerInfo.setOfferCode( offer.getOfferCode() );
        offerInfo.setOfferType( offer.getOfferType() );
        offerInfo.setOfferName( offer.getOfferName() );

        return offerInfo;
    }

    protected List<OfferInfo> offerListToOfferInfoList(List<Offer> list) {
        if ( list == null ) {
            return null;
        }

        List<OfferInfo> list1 = new ArrayList<OfferInfo>( list.size() );
        for ( Offer offer : list ) {
            list1.add( offerToOfferInfo( offer ) );
        }

        return list1;
    }

    protected VendorInfo vendorToVendorInfo(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorInfo vendorInfo = new VendorInfo();

        vendorInfo.setId( vendor.getId() );
        vendorInfo.setFullName( vendor.getFullName() );
        vendorInfo.setVendorCode( vendor.getVendorCode() );

        return vendorInfo;
    }

    protected List<VendorInfo> vendorListToVendorInfoList(List<Vendor> list) {
        if ( list == null ) {
            return null;
        }

        List<VendorInfo> list1 = new ArrayList<VendorInfo>( list.size() );
        for ( Vendor vendor : list ) {
            list1.add( vendorToVendorInfo( vendor ) );
        }

        return list1;
    }
}
