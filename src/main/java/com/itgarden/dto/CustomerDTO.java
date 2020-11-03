package com.itgarden.dto;

import com.itgarden.entity.User;
import lombok.Data;

@Data
public class CustomerDTO extends BaseDTO {

    private String fullName;

    private String customerCode;

    private UserDTO user;
}
