package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itgarden.entity.BaseObject;
import com.itgarden.entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class AddressDto extends BaseDto {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String landmark;

    private String mobile;
}
