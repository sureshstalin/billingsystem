package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto extends BaseDto {

    private String fullName;

    private String employeeCode;

    private UserDto user;
}
