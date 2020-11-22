package com.itgarden.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class EntityCode extends BaseObject {

    @Column(name = "code",unique = true,nullable = false)
    String code;
}
