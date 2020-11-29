package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
 * Created by Suresh Stalin on 14 / Oct / 2020.
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseInfo implements Serializable {

    protected  Long id;
    protected String type;
}
