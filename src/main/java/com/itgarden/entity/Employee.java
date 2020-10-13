package com.itgarden.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee extends BaseObject {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "employee_code", nullable = false)
    private String employeeCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
