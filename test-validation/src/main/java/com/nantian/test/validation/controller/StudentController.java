package com.nantian.test.validation.controller;

import com.nantian.test.validation.pojo.Student;
import com.nantian.test.validation.valodation.ValidGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/17
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping(value = "/valid/add")
    public String add(@Validated(value = ValidGroup.Crud.Create.class) @RequestBody Student student) {
        System.out.println(student);
        return "test3 valid success";
    }

    @PostMapping(value = "/valid/update")
    public String update(@Validated(value = ValidGroup.Crud.Update.class) @RequestBody Student student) {
        System.out.println(student);
        return "test4 valid success";
    }

}
