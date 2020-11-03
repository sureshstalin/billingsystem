package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import com.itgarden.entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class VendorDTO extends BaseDTO {

    private String fullName;

    private String vendorCode;

    private UserDTO user;
}
