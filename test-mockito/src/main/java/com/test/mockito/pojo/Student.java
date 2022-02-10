package com.test.mockito.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long studentId;
    private String name;
    private Long age;
    private String describe;
}
