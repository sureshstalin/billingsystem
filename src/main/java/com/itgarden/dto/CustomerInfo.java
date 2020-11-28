package com.itgarden.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 10 / Oct / 2020.
 */

@Getter
@Setter
public class CustomerInfo extends BaseInfo {

    private String fullName;

    private String customerCode;

    private UserInfo user;
}
