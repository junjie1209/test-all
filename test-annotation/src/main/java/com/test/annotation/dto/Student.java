package com.test.annotation.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/7/27
 */
@Data
@Valid
public class Student {
    private String id;

    @NotBlank
    private String userName;

    @Pattern(regexp = "^[0-9]$", message = "num必须是数字")
    private String num;
}
