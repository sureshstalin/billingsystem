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
    date = "2020-11-02T21:19:55+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDTO.getId() );
        employee.setFullName( employeeDTO.getFullName() );
        employee.setEmployeeCode( employeeDTO.getEmployeeCode() );
        employee.setUser( userDTOToUser( employeeDTO.getUser() ) );

        return employee;
    }

    @Override
    public EmployeeDTO employeeToDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.setFullName( employee.getFullName() );
        employeeDTO.setEmployeeCode( employee.getEmployeeCode() );
        employeeDTO.setUser( userToUserDTO( employee.getUser() ) );

        return employeeDTO;
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDTO.getId() );
        address.setAddress1( addressDTO.getAddress1() );
        address.setAddress2( addressDTO.getAddress2() );
        address.setCity( addressDTO.getCity() );
        address.setState( addressDTO.getState() );
        address.setCountry( addressDTO.getCountry() );
        address.setLandmark( addressDTO.getLandmark() );
        address.setMobile( addressDTO.getMobile() );

        return address;
    }

    protected List<Address> addressDTOListToAddressList(List<AddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressDTO addressDTO : list ) {
            list1.add( addressDTOToAddress( addressDTO ) );
        }

        return list1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setName( roleDTO.getName() );
        role.setDescription( roleDTO.getDescription() );

        return role;
    }

    protected List<Role> roleDTOListToRoleList(List<RoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDTO roleDTO : list ) {
            list1.add( roleDTOToRole( roleDTO ) );
        }

        return list1;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setEmailId( userDTO.getEmailId() );
        user.setFirstName( userDTO.getFirstName() );
        user.setPassword( userDTO.getPassword() );
        user.setMiddleName( userDTO.getMiddleName() );
        user.setLastName( userDTO.getLastName() );
        user.setMobileNo( userDTO.getMobileNo() );
        user.setAddressList( addressDTOListToAddressList( userDTO.getAddressList() ) );
        user.setRoles( roleDTOListToRoleList( userDTO.getRoles() ) );

        return user;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId( address.getId() );
        addressDTO.setAddress1( address.getAddress1() );
        addressDTO.setAddress2( address.getAddress2() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setState( address.getState() );
        addressDTO.setCountry( address.getCountry() );
        addressDTO.setLandmark( address.getLandmark() );
        addressDTO.setMobile( address.getMobile() );

        return addressDTO;
    }

    protected List<AddressDTO> addressListToAddressDTOList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDTO> list1 = new ArrayList<AddressDTO>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressDTO( address ) );
        }

        return list1;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setDescription( role.getDescription() );

        return roleDTO;
    }

    protected List<RoleDTO> roleListToRoleDTOList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDTO> list1 = new ArrayList<RoleDTO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDTO( role ) );
        }

        return list1;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmailId( user.getEmailId() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setMiddleName( user.getMiddleName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setMobileNo( user.getMobileNo() );
        userDTO.setAddressList( addressListToAddressDTOList( user.getAddressList() ) );
        userDTO.setRoles( roleListToRoleDTOList( user.getRoles() ) );

        return userDTO;
    }
}
