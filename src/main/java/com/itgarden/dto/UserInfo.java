package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/*
 * Created by Suresh Stalin on 15 / Oct / 2020.
 */

@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo extends BaseInfo {


    private String emailId;

    private String password;

    private String rePassword;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobileNo;

    private List<AddressInfo> addressList;

    private List<RoleInfo> roles;

    private EmployeeInfo employee;

    private String userType;

}
