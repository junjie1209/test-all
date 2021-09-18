package com.test.flink.sql.stream.job.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * 根据入参zoneId将入参date转换为对应时区时间
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class StringToTimestampUdf extends ScalarFunction {
    public String eval(String date,String zoneId){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(offsetDateTime.toInstant(), ZoneId.of(zoneId));
        return localDateTime.toString();
    }
}
