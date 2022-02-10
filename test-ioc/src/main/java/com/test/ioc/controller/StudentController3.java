package com.test.ioc.controller;

import com.test.ioc.service.StudentService;
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
public class StudentController3 {

    private StudentService studentService;

    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/get-student3")
    public void getStudent(){
        studentService.getStudent();
    }

}
