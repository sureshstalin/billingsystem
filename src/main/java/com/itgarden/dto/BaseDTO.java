package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Serializable {

    protected  Long id;
    protected String type;
}
