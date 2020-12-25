package com.itgarden.mapper.impl;

import com.itgarden.dto.AddressInfo;
import com.itgarden.dto.EmployeeInfo;
import com.itgarden.dto.RoleInfo;
import com.itgarden.dto.UserInfo;
import com.itgarden.entity.Address;
import com.itgarden.entity.Employee;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import com.itgarden.mapper.EmployeeMapper;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T09:21:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeInfoToEmployee(EmployeeInfo employeeInfo) {
        if ( employeeInfo == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeInfo.getId() );
        employee.setFullName( employeeInfo.getFullName() );
        employee.setEmployeeCode( employeeInfo.getEmployeeCode() );
        employee.setUser( userInfoToUser( employeeInfo.getUser() ) );

        return employee;
    }

    @Override
    public EmployeeInfo employeeToEmployeeInfo(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeInfo employeeInfo = new EmployeeInfo();

        employeeInfo.setId( employee.getId() );
        employeeInfo.setFullName( employee.getFullName() );
        employeeInfo.setEmployeeCode( employee.getEmployeeCode() );
        employeeInfo.setUser( userToUserInfo( employee.getUser() ) );

        return employeeInfo;
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
}
