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
@Table(name = "customer")
public class Customer extends BaseObject {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "customer_code", nullable = false)
    private String customerCode;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
