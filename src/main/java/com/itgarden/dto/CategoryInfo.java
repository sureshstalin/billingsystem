package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryInfo extends BaseInfo {


    private String categoryCode;
    @NotEmpty(message = "Category name can't be empty")
    private String name;

    @NotEmpty(message = "Category Description can't be empty")
    private String description;
}
