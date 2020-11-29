package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "vendor")
public class Vendor extends BaseObject {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
