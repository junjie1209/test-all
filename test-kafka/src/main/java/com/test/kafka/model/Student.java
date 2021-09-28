package com.test.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/22
 */
@Data
@AllArgsConstructor
public class Student {
    private Integer studentNumber;
    private String name;
    private Integer age;
}
