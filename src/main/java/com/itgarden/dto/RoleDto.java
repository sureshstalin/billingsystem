package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto extends BaseDto {

    private String name;

    private String description;
}
