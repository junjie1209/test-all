package com.test.ioc.controller;

import com.test.ioc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/25
 */
@RestController
@RequestMapping("/test-ioc")
public class StudentController1 {

    @Autowired
    private StudentService studentService;


    @PostMapping("/get-student1")
    public void getStudent(){
        studentService.getStudent();
    }

}
