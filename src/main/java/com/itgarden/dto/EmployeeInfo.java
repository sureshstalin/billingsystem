package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 15 / Oct / 2020.
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInfo extends BaseInfo {

    private String fullName;

    private String employeeCode;

    private UserInfo user;
}
