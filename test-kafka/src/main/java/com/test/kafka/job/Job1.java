package com.test.kafka.job;

import com.test.kafka.sender.Sender;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/22
 */
public class Job1 {
    public static void main(String[] args) {
        new Thread(new Sender(), "demo1-simulation-data").start();
    }
}
