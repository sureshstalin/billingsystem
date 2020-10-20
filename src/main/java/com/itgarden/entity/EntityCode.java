package com.itgarden.entity;

import javax.persistence.Column;

public class EntityCode extends BaseObject {

    @Column(name = "code",unique = true,nullable = false)
    String code;
}
