package com.itgarden.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
