package com.test.annotation.service;

import com.test.annotation.dto.Student;

import javax.validation.Valid;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/7/27
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId("1001");
        student.setUserName(null);
        student.setNum("wqwqwq");
        System.out.println(student);
    }
}
