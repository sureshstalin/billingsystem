package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

@Getter
@Setter
public class EntityCode extends BaseObject {

    @Column(name = "code",unique = true,nullable = false)
    String code;
}
