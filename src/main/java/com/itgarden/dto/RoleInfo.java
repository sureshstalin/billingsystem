package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/*
 * Created by Suresh Stalin on 10 / Oct / 2020.
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleInfo extends BaseInfo {

    private String name;

    private String description;
}
