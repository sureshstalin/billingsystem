package com.itgarden.mapper.impl;

import com.itgarden.dto.AddressDTO;
import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.RoleDTO;
import com.itgarden.dto.UserDTO;
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
    date = "2020-10-17T05:50:19+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO employeeToDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDto = new EmployeeDTO();

        employeeDto.setId( employee.getId() );
        employeeDto.setType( employee.getFlowType() );
        employeeDto.setFullName( employee.getFullName() );
        employeeDto.setEmployeeCode( employee.getEmployeeCode() );
        employeeDto.setUser( userToUserDto( employee.getUser() ) );

        return employeeDto;
    }

    @Override
    public Employee dToToEmployee(EmployeeDTO employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setFlowType( employeeDto.getType() );
        employee.setFullName( employeeDto.getFullName() );
        employee.setEmployeeCode( employeeDto.getEmployeeCode() );
        employee.setUser( userDtoToUser( employeeDto.getUser() ) );

//        afterMapping( employee, employeeDto );

        return employee;
    }

    protected AddressDTO addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDto = new AddressDTO();

        addressDto.setId( address.getId() );
        addressDto.setType( address.getFlowType() );
        addressDto.setAddress1( address.getAddress1() );
        addressDto.setAddress2( address.getAddress2() );
        addressDto.setCity( address.getCity() );
        addressDto.setState( address.getState() );
        addressDto.setCountry( address.getCountry() );
        addressDto.setLandmark( address.getLandmark() );
        addressDto.setMobile( address.getMobile() );

        return addressDto;
    }

    protected List<AddressDTO> addressListToAddressDtoList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDTO> list1 = new ArrayList<AddressDTO>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressDto( address ) );
        }

        return list1;
    }

    protected RoleDTO roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDto = new RoleDTO();

        roleDto.setId( role.getId() );
        roleDto.setType( role.getFlowType() );
        roleDto.setName( role.getName() );
        roleDto.setDescription( role.getDescription() );

        return roleDto;
    }

    protected List<RoleDTO> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDTO> list1 = new ArrayList<RoleDTO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }

    protected UserDTO userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDto = new UserDTO();

        userDto.setId( user.getId() );
        userDto.setEmailId( user.getEmailId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setMiddleName( user.getMiddleName() );
        userDto.setLastName( user.getLastName() );
        userDto.setMobileNo( user.getMobileNo() );
        userDto.setType( user.getFlowType() );
        userDto.setAddressList( addressListToAddressDtoList( user.getAddressList() ) );
        userDto.setRoles( roleListToRoleDtoList( user.getRoles() ) );

        return userDto;
    }

    protected Address addressDtoToAddress(AddressDTO addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDto.getId() );
        address.setFlowType( addressDto.getType() );
        address.setAddress1( addressDto.getAddress1() );
        address.setAddress2( addressDto.getAddress2() );
        address.setCity( addressDto.getCity() );
        address.setState( addressDto.getState() );
        address.setCountry( addressDto.getCountry() );
        address.setLandmark( addressDto.getLandmark() );
        address.setMobile( addressDto.getMobile() );

        return address;
    }

    protected List<Address> addressDtoListToAddressList(List<AddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressDTO addressDto : list ) {
            list1.add( addressDtoToAddress( addressDto ) );
        }

        return list1;
    }

    protected Role roleDtoToRole(RoleDTO roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setFlowType( roleDto.getType());
        role.setName( roleDto.getName() );
        role.setDescription( roleDto.getDescription() );

        return role;
    }

    protected List<Role> roleDtoListToRoleList(List<RoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDTO roleDto : list ) {
            list1.add( roleDtoToRole( roleDto ) );
        }

        return list1;
    }

    protected User userDtoToUser(UserDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFlowType( userDto.getType() );
        user.setEmailId( userDto.getEmailId() );
        user.setFirstName( userDto.getFirstName() );
        user.setMiddleName( userDto.getMiddleName() );
        user.setLastName( userDto.getLastName() );
        user.setMobileNo( userDto.getMobileNo() );
        user.setAddressList( addressDtoListToAddressList( userDto.getAddressList() ) );
        user.setRoles( roleDtoListToRoleList( userDto.getRoles() ) );

        return user;
    }
}
