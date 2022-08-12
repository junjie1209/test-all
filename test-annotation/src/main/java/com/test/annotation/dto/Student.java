package com.test.annotation.dto;

import com.test.annotation.validation.InetPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/7/27
 */
@Data
@Validated
public class Student {
    private String id;

    private String userName;

    private String num;

    @InetPattern(pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}", message = "字段必须为ip格式")
    private String ip;
}
