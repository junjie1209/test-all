package com.test.flink.sql.stream.demo;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.Arrays;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/10/15
 */
public class FromCollection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> stream = env.fromCollection(Arrays.asList("4", "5", "6"));
        stream.print("ds");
        env.execute("123");
    }
}
