package com.itgarden.dto;

import com.itgarden.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
public class CategoryInfo extends BaseInfo {

    private String categoryCode;

    private String name;

    private String description;
}
