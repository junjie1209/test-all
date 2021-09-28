package com.test.flink.sql.stream.demo;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * kafka csv to mysql
 *
 * @author: Junjie Zhang
 * @date: 2021/9/22
 */
public class KafkaCsvToMysql {
    public static void main(String[] args) throws Exception {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);
        tableEnv.getConfig().getConfiguration().setString("pipeline.name", "Demo-KafkaCsvToMysql");

        String ddlSource = "CREATE TABLE kafka_source (\n" +
                "    `studentNumber` BIGINT,\n" +
                "    `name` STRING,\n" +
                "    `age` BIGINT\n" +
                ") WITH (\n" +
                "    'connector' = 'kafka',\n" +
                "    'topic' = 'demo1-simulation-data',\n" +
                "    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
                "    'properties.group.id' = '1001',\n" +
                "    'scan.startup.mode' = 'latest-offset',\n" +
                "    'format' = 'json'\n" +
                ")";

        String ddlSink = "CREATE TABLE mysql_sink (\n" +
                "  `studentNumber` BIGINT,\n" +
                "  `name` STRING,\n" +
                "  `age` BIGINT\n" +
                ") WITH (\n" +
                "   'connector' = 'jdbc',\n" +
                "   'driver' = 'com.mysql.cj.jdbc.Driver',\n" +
                "   'url' = 'jdbc:mysql://10.12.40.14:3306/galaxy_v2?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull',\n" +
                "   'table-name' = 'kafka_csv_mysql',\n" +
                "   'username' = 'root',\n" +
                "   'password' = 'Passw0rd'\n" +
                ")";

        String sql = "insert into mysql_sink\n" +
                "select\n" +
                "`studentNumber`,\n" +
                "`name`,\n" +
                "`age`\n" +
                "from kafka_source";

        tableEnv.executeSql(ddlSource);
        tableEnv.executeSql(ddlSink);
        tableEnv.executeSql(sql);
    }
}
