package com.mettre.usually.pojo;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BasePage {

    @Min(value = 1, message = "分页入参错误")
    private Integer page;

    @Min(value = 1, message = "分页入参错误")
    private Integer size;
}
