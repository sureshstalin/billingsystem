package com.itgarden.mapper.impl;

import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.PurchaseOrderMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T09:21:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Override
    public PurchaseOrder purchaseOrderInfoToPurchaseOrder(PurchaseOrderInfo purchaseOrderInfo) {
        if ( purchaseOrderInfo == null ) {
            return null;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setId( purchaseOrderInfo.getId() );
        purchaseOrder.setDeleted( purchaseOrderInfo.isDeleted() );
        purchaseOrder.setDateCreated( purchaseOrderInfo.getDateCreated() );
        purchaseOrder.setDateModified( purchaseOrderInfo.getDateModified() );
        purchaseOrder.setFlowType( purchaseOrderInfo.getFlowType() );
        purchaseOrder.setProductName( purchaseOrderInfo.getProductName() );
        purchaseOrder.setProductDescription( purchaseOrderInfo.getProductDescription() );
        purchaseOrder.setQuantity( purchaseOrderInfo.getQuantity() );
        purchaseOrder.setUnitPrice( purchaseOrderInfo.getUnitPrice() );
        purchaseOrder.setPurchaseOrderCode( purchaseOrderInfo.getPurchaseOrderCode() );
        purchaseOrder.setVendor( vendorInfoToVendor( purchaseOrderInfo.getVendor() ) );
        purchaseOrder.setTax( taxInfoToTax( purchaseOrderInfo.getTax() ) );
        purchaseOrder.setTaxAmount( purchaseOrderInfo.getTaxAmount() );
        purchaseOrder.setTotalAmount( purchaseOrderInfo.getTotalAmount() );
        purchaseOrder.setCategory( categoryInfoToCategory( purchaseOrderInfo.getCategory() ) );
        purchaseOrder.setGrandTotal( purchaseOrderInfo.getGrandTotal() );
        purchaseOrder.setPurchaseOrderStatus( purchaseOrderInfo.getPurchaseOrderStatus() );

        return purchaseOrder;
    }

    @Override
    public PurchaseOrderInfo purchaseOrderToPurchaseOrderInfo(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

        purchaseOrderInfo.setId( purchaseOrder.getId() );
        purchaseOrderInfo.setDeleted( purchaseOrder.isDeleted() );
        purchaseOrderInfo.setDateCreated( purchaseOrder.getDateCreated() );
        purchaseOrderInfo.setDateModified( purchaseOrder.getDateModified() );
        purchaseOrderInfo.setFlowType( purchaseOrder.getFlowType() );
        purchaseOrderInfo.setProductName( purchaseOrder.getProductName() );
        purchaseOrderInfo.setProductDescription( purchaseOrder.getProductDescription() );
        purchaseOrderInfo.setQuantity( purchaseOrder.getQuantity() );
        purchaseOrderInfo.setUnitPrice( purchaseOrder.getUnitPrice() );
        purchaseOrderInfo.setPurchaseOrderCode( purchaseOrder.getPurchaseOrderCode() );
        purchaseOrderInfo.setVendor( vendorToVendorInfo( purchaseOrder.getVendor() ) );
        purchaseOrderInfo.setTax( taxToTaxInfo( purchaseOrder.getTax() ) );
        purchaseOrderInfo.setTaxAmount( purchaseOrder.getTaxAmount() );
        purchaseOrderInfo.setTotalAmount( purchaseOrder.getTotalAmount() );
        purchaseOrderInfo.setCategory( categoryToCategoryInfo( purchaseOrder.getCategory() ) );
        purchaseOrderInfo.setGrandTotal( purchaseOrder.getGrandTotal() );
        purchaseOrderInfo.setPurchaseOrderStatus( purchaseOrder.getPurchaseOrderStatus() );

        return purchaseOrderInfo;
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
