package com.itgarden.dto;

import lombok.Data;

@Data
public class CustomerInfo extends BaseInfo {

    private String fullName;

    private String customerCode;

    private UserInfo user;
}
