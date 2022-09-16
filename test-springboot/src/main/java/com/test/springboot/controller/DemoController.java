package com.test.springboot.controller;

import com.test.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/9/6
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/add")
    public Long add() {
        return demoService.addDemo();
    }

    @GetMapping("/reduce")
    public Long reduce() {
        return demoService.reduceDemo();
    }

    @GetMapping("/add/threadLocal")
    public Long addThreadLocal() {
        return demoService.addThreadLocal();
    }

    @GetMapping("/reduce")
    public Long reduceThreadLocal() {
        return demoService.reduceThreadLocal();
    }

}
