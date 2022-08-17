package com.test.annotation.controller;

import com.test.annotation.dto.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "springboot测试")
public class StudentController {

    @PostMapping("/add")
    @Operation(summary = "新增Student")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<?> addStudent(@RequestBody @Validated Student student) {
        System.out.println(student);
        return ResponseEntity.ok(student);
    }

}
