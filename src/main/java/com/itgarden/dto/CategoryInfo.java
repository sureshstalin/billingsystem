package com.itgarden.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Getter
@Setter
public class CategoryInfo extends BaseInfo {


    private String categoryCode;
    @NotEmpty(message = "Category name can't be empty")
    private String name;

    @NotEmpty(message = "Category Description can't be empty")
    private String description;
}
