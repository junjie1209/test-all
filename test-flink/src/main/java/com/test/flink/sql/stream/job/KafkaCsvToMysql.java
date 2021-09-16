package com.test.flink.sql.stream.job;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * kafka csv to mysql
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class KafkaCsvToMysql {
    public static void main(String[] args) throws Exception {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);

        String ddlSource = "CREATE TABLE kafka_source (\n" +
                "    `name` STRING,\n" +
                "    `tel` STRING,\n" +
                "    `age` BIGINT,\n" +
                "    `pay` DECIMAL(10,2),\n" +
                "    `date` STRING,\n" +
                "    `tags` STRING,\n" +
                "    `comments` STRING,\n" +
                "    `lover.name` STRING,\n" +
                "    `lover.gender` STRING\n" +
                ") WITH (\n" +
                "    'connector' = 'kafka', -- 使用 kafka connector\n" +
                "    'topic' = 'test-zjj-flink-job1-kafkaCsv',  -- kafka topic\n" +
                "    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
                "    'properties.group.id' = '1001',\n" +
                "    'scan.startup.mode' = 'latest-offset',  -- 读取数据的位置\n" +
                "    'format' = 'csv'  -- 数据源格式为 json\n" +
                ")";

        String ddlSink = "CREATE TABLE mysql_sink (\n" +
                "  `name` STRING,\n" +
                "  `tel` STRING,\n" +
                "  `age` BIGINT,\n" +
                "  `pay` DECIMAL(10,2),\n" +
                "  `date` STRING,\n" +
                "  `tags` STRING,\n" +
                "  `comments` STRING,\n" +
                "  `lover_name` STRING,\n" +
                "  `lover_gender` STRING\n" +
                ") WITH (\n" +
                "   'connector' = 'jdbc',\n" +
                "   'driver' = 'com.mysql.cj.jdbc.Driver',\n" +
                "   'url' = 'jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull',\n" +
                "   'table-name' = 'kafka_csv_mysql',\n" +
                "   'username' = 'root',\n" +
                "   'password' = '123456'\n" +
                ")";

        String sql = "insert into mysql_sink\n" +
                "select\n" +
                "name,\n" +
                "tel,\n" +
                "age,\n" +
                "pay,\n" +
                "`date`,\n" +
                "tags,\n" +
                "comments,\n	" +
                "`lover.name`,\n" +
                "`lover.gender`\n" +
                "from kafka_source";

        tableEnv.executeSql(ddlSource);
        tableEnv.executeSql(ddlSink);
        tableEnv.executeSql(sql);
    }
}
