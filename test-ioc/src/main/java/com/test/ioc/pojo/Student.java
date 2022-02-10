package com.test.ioc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String studentId;
    private String name;
    private Long age;
    private String describe;
}
