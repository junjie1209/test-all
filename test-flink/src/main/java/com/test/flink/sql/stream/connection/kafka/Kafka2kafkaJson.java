package com.test.flink.sql.stream.connection.kafka;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/1
 */
public class Kafka2kafkaJson {
    public static void main(String[] args) {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);

        String ddlSource = "CREATE TABLE kafka_source (\n" +
                "    `event_time` TIMESTAMP(3) METADATA FROM 'timestamp',\n" +
                "    `partition` BIGINT METADATA VIRTUAL,\n"+
                "    `offset` BIGINT METADATA VIRTUAL,\n"+
                "    `user_id` BIGINT,\n" +
                "    `item_id` BIGINT,\n" +
                "    `behavior` STRING\n"+
                ") WITH (\n" +
                "    'connector' = 'kafka', -- 使用 kafka connector\n" +
                "    'topic' = 'test-zjj-flink-source',  -- kafka topic\n" +
                "    'properties.bootstrap.servers' = '10.12.40.1:9092,10.12.40.2:9092,10.12.40.3:9092',\n" +
                "    'properties.group.id' = '1001',\n" +
                "    'scan.startup.mode' = 'latest-offset',  -- 读取数据的位置\n" +
                "    'format' = 'csv',  -- 数据源格式为 json\n" +
                "    'json.fail-on-missing-field' = 'true', -- 字段丢失任务不失败\n" +
                "    'json.ignore-parse-errors' = 'false'  -- 解析失败跳过\n" +
                ")";

        tableEnv.executeSql(ddlSource);
    }
}
