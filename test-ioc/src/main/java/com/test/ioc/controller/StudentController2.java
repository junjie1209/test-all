package com.test.ioc.controller;

import com.test.ioc.service.StudentService;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/25
 */
@RestController
public class StudentController2 {

    final StudentService studentService;

    public StudentController2(StudentService studentService){
        this.studentService = studentService;
    }

}
