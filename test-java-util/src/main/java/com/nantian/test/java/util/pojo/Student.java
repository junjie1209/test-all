package com.nantian.test.java.util.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/3/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String name;
    private String dad;
}
