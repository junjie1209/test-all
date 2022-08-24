package com.test.springboot.starter;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/24
 */
public class TestSpringBootService {
    private String testSpringBoot;

    public TestSpringBootService(String testSpringBoot) {
        this.testSpringBoot = testSpringBoot;
    }

    public String getService() {
        return testSpringBoot;
    }
}
