package com.test.springboot.service;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/9/6
 */
@Service
public class DemoService {
    private Long count = 0L;

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public Long addDemo() {
        count = count + 1;
        return count;

    }

    public Long reduceDemo() {
        count = count - 1;
        return count;
    }

    public Long addThreadLocal() {
        threadLocal.set(threadLocal.get() + 1L);
        return threadLocal.get();

    }

    public Long reduceThreadLocal() {
        threadLocal.set(threadLocal.get() - 1L);
        return threadLocal.get();
    }
}
