package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseObject {

    @Column(name = "emailId", nullable = false)
    private String emailId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addressList;

   @ManyToMany(targetEntity = Role.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="user_role",
            joinColumns=
            @JoinColumn( name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))
    private List<Role> roles;
}
