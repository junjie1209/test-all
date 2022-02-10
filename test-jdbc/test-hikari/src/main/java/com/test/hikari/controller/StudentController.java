package com.test.hikari.controller;

import com.test.hikari.pojo.Student;
import com.test.hikari.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
@RestController
@RequestMapping("/test-jdbc/test-hikari/student")
public class StudentController {
      @Autowired
      private StudentService studentService;

      @PostMapping("/insert")
      public Student insertStudent(@RequestBody Student student){
            return studentService.insert(student);
      }

      @GetMapping("/list")
      public List<Student> listStudent(){
           return studentService.getAll();
      }


}
