package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO extends BaseDTO {

    private String fullName;

    private String employeeCode;

    private UserDTO user;
}
