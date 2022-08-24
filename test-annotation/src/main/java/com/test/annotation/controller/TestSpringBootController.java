package com.test.annotation.controller;

import com.test.springboot.starter.TestSpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/24
 */
@RestController
public class TestSpringBootController {
    @Autowired
    private TestSpringBootService testSpringBootService;

    @GetMapping("demo")
    public ResponseEntity demo() {
        String service = testSpringBootService.getService();
        return new ResponseEntity(service, HttpStatus.OK);
    }

    /*public TestSpringBootController(TestSpringBootService testSpringBootService) {
        this.testSpringBootService = testSpringBootService;
    }*/
}
