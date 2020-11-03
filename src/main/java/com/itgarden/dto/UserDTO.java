package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itgarden.validator.EmptyOrNullCheck;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO {


    @NotBlank(message = "Email Address can't be empty")
    @Size(min = 1, max = 100)
//    @Pattern(regexp="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$",
//            message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "Password can't empty")
    private String password;

    @NotBlank(message = "Password can't empty")
    private String rePassword;

    @EmptyOrNullCheck
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
