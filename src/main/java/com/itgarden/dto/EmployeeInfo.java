package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInfo extends BaseInfo {

    private String fullName;

    private String employeeCode;

    private UserInfo user;
}
