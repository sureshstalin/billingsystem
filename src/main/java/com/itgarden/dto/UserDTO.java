package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itgarden.validator.EmptyOrNullCheck;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO {


    @NotBlank(message = "Email Address can't be empty")
    private String emailId;

    @NotBlank(message = "First Name can't be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last Name can't be empty")
    private String lastName;

    @NotBlank(message = "Mobile No Name can't be empty")
    private String mobileNo;

    private List<AddressDTO> addressList;

    private List<RoleDTO> roles;

    private EmployeeDTO employee;

}
