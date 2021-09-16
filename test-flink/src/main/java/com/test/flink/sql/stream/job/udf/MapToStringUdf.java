package com.test.flink.sql.stream.job.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.util.Map;

/**
 * 将Map<String,String>转换为String
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class MapToStringUdf extends ScalarFunction {
    public String eval(Map<String,String> map){
        return map.toString();
    }
}
