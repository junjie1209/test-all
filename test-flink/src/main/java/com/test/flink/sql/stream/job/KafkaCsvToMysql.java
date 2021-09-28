package com.test.flink.sql.stream.job;

import com.test.flink.sql.stream.job.udf.StringToTimestampUdf;
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
        tableEnv.getConfig().getConfiguration().setString("pipeline.name", "Common.jobName");

        tableEnv.createFunction("stringToTimestampUdf", StringToTimestampUdf.class);

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
                "    'connector' = 'kafka',\n" +
                "    'topic' = 'test-zjj-flink-job1-kafkaCsv',\n" +
                "    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
                "    'properties.group.id' = '1001',\n" +
                "    'scan.startup.mode' = 'latest-offset',\n" +
                "    'format' = 'csv'\n" +
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
                "stringToTimestampUdf(`date`,'GMT+8'),\n" +
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
