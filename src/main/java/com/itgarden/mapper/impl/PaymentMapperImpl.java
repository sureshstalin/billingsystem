package com.itgarden.mapper.impl;

import com.itgarden.dto.*;
import com.itgarden.entity.*;
import com.itgarden.mapper.PaymentMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-26T02:23:18+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment paymentInfoToPayment(PaymentInfo paymentInfo) {
        if ( paymentInfo == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setId( paymentInfo.getId() );
        payment.setDeleted( paymentInfo.isDeleted() );
        payment.setDateCreated( paymentInfo.getDateCreated() );
        payment.setDateModified( paymentInfo.getDateModified() );
        payment.setFlowType( paymentInfo.getFlowType() );
        payment.setBiller( billerInfoToBiller( paymentInfo.getBiller() ) );
        payment.setProductItem( productItemInfoToProductItem( paymentInfo.getProductItem() ) );
        payment.setTax( paymentInfo.getTax() );
        payment.setPrice( paymentInfo.getPrice() );
        payment.setTotalPrice( paymentInfo.getTotalPrice() );
        payment.setTaxAmount( paymentInfo.getTaxAmount() );
        payment.setProductId( paymentInfo.getProductId() );

        return payment;
    }

    @Override
    public PaymentInfo paymentToPaymentInfo(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentInfo paymentInfo = new PaymentInfo();

        paymentInfo.setId( payment.getId() );
        paymentInfo.setDeleted( payment.isDeleted() );
        paymentInfo.setDateCreated( payment.getDateCreated() );
        paymentInfo.setDateModified( payment.getDateModified() );
        paymentInfo.setFlowType( payment.getFlowType() );
        paymentInfo.setBiller( billerToBillerInfo( payment.getBiller() ) );
        paymentInfo.setProductItem( productItemToProductItemInfo( payment.getProductItem() ) );
        paymentInfo.setTax( payment.getTax() );
        paymentInfo.setPrice( payment.getPrice() );
        paymentInfo.setTotalPrice( payment.getTotalPrice() );
        paymentInfo.setTaxAmount( payment.getTaxAmount() );
        paymentInfo.setProductId( payment.getProductId() );

        return paymentInfo;
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

    protected Customer customerInfoToCustomer(CustomerInfo customerInfo) {
        if ( customerInfo == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerInfo.getId() );
        customer.setFullName( customerInfo.getFullName() );
        customer.setCustomerCode( customerInfo.getCustomerCode() );
        customer.setUser( userInfoToUser( customerInfo.getUser() ) );

        return customer;
    }

    protected Biller billerInfoToBiller(BillerInfo billerInfo) {
        if ( billerInfo == null ) {
            return null;
        }

        Biller biller = new Biller();

        biller.setId( billerInfo.getId() );
        biller.setBillNo( billerInfo.getBillNo() );
        biller.setCustomer( customerInfoToCustomer( billerInfo.getCustomer() ) );
        biller.setGrandTotal( billerInfo.getGrandTotal() );
        biller.setQuantity( billerInfo.getQuantity() );
        biller.setTotalTaxAmount( billerInfo.getTotalTaxAmount() );

        return biller;
    }

    protected ProductItem productItemInfoToProductItem(ProductItemInfo productItemInfo) {
        if ( productItemInfo == null ) {
            return null;
        }

        ProductItem productItem = new ProductItem();

        productItem.setId( productItemInfo.getId() );
        productItem.setProductItemCode( productItemInfo.getProductItemCode() );
        productItem.setStockStatus( productItemInfo.getStockStatus() );

        return productItem;
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

    protected CustomerInfo customerToCustomerInfo(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerInfo customerInfo = new CustomerInfo();

        customerInfo.setId( customer.getId() );
        customerInfo.setFullName( customer.getFullName() );
        customerInfo.setCustomerCode( customer.getCustomerCode() );
        customerInfo.setUser( userToUserInfo( customer.getUser() ) );

        return customerInfo;
    }

    protected BillerInfo billerToBillerInfo(Biller biller) {
        if ( biller == null ) {
            return null;
        }

        BillerInfo billerInfo = new BillerInfo();

        billerInfo.setId( biller.getId() );
        billerInfo.setBillNo( biller.getBillNo() );
        billerInfo.setCustomer( customerToCustomerInfo( biller.getCustomer() ) );
        billerInfo.setGrandTotal( biller.getGrandTotal() );
        billerInfo.setQuantity( biller.getQuantity() );
        billerInfo.setTotalTaxAmount( biller.getTotalTaxAmount() );

        return billerInfo;
    }

    protected ProductItemInfo productItemToProductItemInfo(ProductItem productItem) {
        if ( productItem == null ) {
            return null;
        }

        ProductItemInfo productItemInfo = new ProductItemInfo();

        productItemInfo.setId( productItem.getId() );
        productItemInfo.setProductItemCode( productItem.getProductItemCode() );
        productItemInfo.setStockStatus( productItem.getStockStatus() );

        return productItemInfo;
    }
}
