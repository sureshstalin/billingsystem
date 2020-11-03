package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO {


    private String emailId;

    private String password;

    private String rePassword;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobileNo;

    private List<AddressDTO> addressList;

    private List<RoleDTO> roles;

    private EmployeeDTO employee;

}
