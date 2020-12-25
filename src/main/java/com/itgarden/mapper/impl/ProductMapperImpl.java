package com.itgarden.mapper.impl;

import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.ProductMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T09:21:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
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
        product.setProductCode( productInfo.getProductCode() );
        product.setPrice( productInfo.getPrice() );
        if ( productInfo.getStockCount() != null ) {
            product.setStockCount( productInfo.getStockCount() );
        }
        product.setTax( taxInfoToTax( productInfo.getTax() ) );
        product.setOffers( offerInfoListToOfferList( productInfo.getOffers() ) );
        product.setVendors( vendorInfoListToVendorList( productInfo.getVendors() ) );
        product.setCategory( categoryInfoToCategory( productInfo.getCategory() ) );

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
        productInfo.setProductCode( product.getProductCode() );
        if ( product.getPrice() != null ) {
            productInfo.setPrice( product.getPrice() );
        }
        productInfo.setTax( taxToTaxInfo( product.getTax() ) );
        productInfo.setOffers( offerListToOfferInfoList( product.getOffers() ) );
        productInfo.setVendors( vendorListToVendorInfoList( product.getVendors() ) );
        productInfo.setCategory( categoryToCategoryInfo( product.getCategory() ) );
        productInfo.setStockCount( product.getStockCount() );

        return productInfo;
    }

    protected Tax taxInfoToTax(TaxInfo taxInfo) {
        if ( taxInfo == null ) {
            return null;
        }

        Tax tax = new Tax();

        tax.setId( taxInfo.getId() );
        tax.setHsnCode( taxInfo.getHsnCode() );
        tax.setTaxPercentage( taxInfo.getTaxPercentage() );
        tax.setTaxDescription( taxInfo.getTaxDescription() );

        return tax;
    }

    protected Offer offerInfoToOffer(OfferInfo offerInfo) {
        if ( offerInfo == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setId( offerInfo.getId() );
        offer.setOfferCode( offerInfo.getOfferCode() );
        offer.setOfferName( offerInfo.getOfferName() );
        offer.setOfferDescription( offerInfo.getOfferDescription() );
        offer.setStatus( offerInfo.getStatus() );

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

    protected Address addressInfoToAddress(AddressInfo addressInfo) {
        if ( addressInfo == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressInfo.getId() );
        address.setAddress1( addressInfo.getAddress1() );
        address.setAddress2( addressInfo.getAddress2() );
        address.setCity( addressInfo.getCity() );
        address.setState( addressInfo.getState() );
        address.setCountry( addressInfo.getCountry() );
        address.setLandmark( addressInfo.getLandmark() );
        address.setMobile( addressInfo.getMobile() );

        return address;
    }

    protected List<Address> addressInfoListToAddressList(List<AddressInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressInfo addressInfo : list ) {
            list1.add( addressInfoToAddress( addressInfo ) );
        }

        return list1;
    }

    protected Role roleInfoToRole(RoleInfo roleInfo) {
        if ( roleInfo == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleInfo.getId() );
        role.setName( roleInfo.getName() );
        role.setDescription( roleInfo.getDescription() );

        return role;
    }

    protected List<Role> roleInfoListToRoleList(List<RoleInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleInfo roleInfo : list ) {
            list1.add( roleInfoToRole( roleInfo ) );
        }

        return list1;
    }

    protected User userInfoToUser(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        User user = new User();

        user.setId( userInfo.getId() );
        user.setEmailId( userInfo.getEmailId() );
        user.setFirstName( userInfo.getFirstName() );
        user.setPassword( userInfo.getPassword() );
        user.setMiddleName( userInfo.getMiddleName() );
        user.setLastName( userInfo.getLastName() );
        user.setMobileNo( userInfo.getMobileNo() );
        user.setUserType( userInfo.getUserType() );
        user.setAddressList( addressInfoListToAddressList( userInfo.getAddressList() ) );
        user.setRoles( roleInfoListToRoleList( userInfo.getRoles() ) );

        return user;
    }

    protected Vendor vendorInfoToVendor(VendorInfo vendorInfo) {
        if ( vendorInfo == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setId( vendorInfo.getId() );
        vendor.setFullName( vendorInfo.getFullName() );
        vendor.setVendorCode( vendorInfo.getVendorCode() );
        vendor.setUser( userInfoToUser( vendorInfo.getUser() ) );

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

    protected Category categoryInfoToCategory(CategoryInfo categoryInfo) {
        if ( categoryInfo == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryInfo.getId() );
        category.setCategoryCode( categoryInfo.getCategoryCode() );
        category.setName( categoryInfo.getName() );
        category.setDescription( categoryInfo.getDescription() );

        return category;
    }

    protected TaxInfo taxToTaxInfo(Tax tax) {
        if ( tax == null ) {
            return null;
        }

        TaxInfo taxInfo = new TaxInfo();

        taxInfo.setId( tax.getId() );
        taxInfo.setHsnCode( tax.getHsnCode() );
        taxInfo.setTaxPercentage( tax.getTaxPercentage() );
        taxInfo.setTaxDescription( tax.getTaxDescription() );

        return taxInfo;
    }

    protected OfferInfo offerToOfferInfo(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferInfo offerInfo = new OfferInfo();

        offerInfo.setId( offer.getId() );
        offerInfo.setOfferCode( offer.getOfferCode() );
        offerInfo.setOfferName( offer.getOfferName() );
        offerInfo.setOfferDescription( offer.getOfferDescription() );
        offerInfo.setStatus( offer.getStatus() );

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

    protected AddressInfo addressToAddressInfo(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressInfo addressInfo = new AddressInfo();

        addressInfo.setId( address.getId() );
        addressInfo.setAddress1( address.getAddress1() );
        addressInfo.setAddress2( address.getAddress2() );
        addressInfo.setCity( address.getCity() );
        addressInfo.setState( address.getState() );
        addressInfo.setCountry( address.getCountry() );
        addressInfo.setLandmark( address.getLandmark() );
        addressInfo.setMobile( address.getMobile() );

        return addressInfo;
    }

    protected List<AddressInfo> addressListToAddressInfoList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressInfo> list1 = new ArrayList<AddressInfo>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressInfo( address ) );
        }

        return list1;
    }

    protected RoleInfo roleToRoleInfo(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setId( role.getId() );
        roleInfo.setName( role.getName() );
        roleInfo.setDescription( role.getDescription() );

        return roleInfo;
    }

    protected List<RoleInfo> roleListToRoleInfoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleInfo> list1 = new ArrayList<RoleInfo>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleInfo( role ) );
        }

        return list1;
    }

    protected UserInfo userToUserInfo(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setId( user.getId() );
        userInfo.setEmailId( user.getEmailId() );
        userInfo.setPassword( user.getPassword() );
        userInfo.setFirstName( user.getFirstName() );
        userInfo.setMiddleName( user.getMiddleName() );
        userInfo.setLastName( user.getLastName() );
        userInfo.setMobileNo( user.getMobileNo() );
        userInfo.setAddressList( addressListToAddressInfoList( user.getAddressList() ) );
        userInfo.setRoles( roleListToRoleInfoList( user.getRoles() ) );
        userInfo.setUserType( user.getUserType() );

        return userInfo;
    }

    protected VendorInfo vendorToVendorInfo(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorInfo vendorInfo = new VendorInfo();

        vendorInfo.setId( vendor.getId() );
        vendorInfo.setFullName( vendor.getFullName() );
        vendorInfo.setVendorCode( vendor.getVendorCode() );
        vendorInfo.setUser( userToUserInfo( vendor.getUser() ) );

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

    protected CategoryInfo categoryToCategoryInfo(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryInfo categoryInfo = new CategoryInfo();

        categoryInfo.setId( category.getId() );
        categoryInfo.setCategoryCode( category.getCategoryCode() );
        categoryInfo.setName( category.getName() );
        categoryInfo.setDescription( category.getDescription() );

        return categoryInfo;
    }
}
