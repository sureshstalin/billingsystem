package com.itgarden.entity;

import com.itgarden.common.staticdata.CodeType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_entity_code")
public class AppEntityCode extends BaseObject {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "code_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CodeType codeType;
}
