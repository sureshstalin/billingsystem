package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 14 / Oct / 2020.
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressInfo extends BaseInfo {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String landmark;

    private String mobile;
}
