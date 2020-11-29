package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

@Getter
@Setter
public class VendorInfo extends BaseInfo {

    private String fullName;

    private String vendorCode;

    private UserInfo user;
}
