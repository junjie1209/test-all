package com.test.annotation.controller;

import com.test.annotation.dto.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/11
 */
@RestController
@Validated
@RequestMapping("/student")
public class StudentController {

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Validated Student student) {
        return ResponseEntity.ok(student);
    }

}
