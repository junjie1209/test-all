package com.test.springboot.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/24
 */
@Configuration
public class TestSpringBootConfig {
    @Value("${testSpringBoot}")
    private String testSpringBoot;

    @Bean
    public TestSpringBootService testSpringBootService(){
        return new TestSpringBootService(testSpringBoot);
    }
}
