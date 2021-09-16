package com.test.flink.sql.stream.job.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * 将Map<String,String>转换为String
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class StringToTimestampUdf extends ScalarFunction {
    public String eval(String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(offsetDateTime.toInstant(), ZoneId.of("GMT+8"));
        return localDateTime.toString();
    }
}
