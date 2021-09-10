package com.test.flink.sql.stream.connection.hbase;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/7
 */
public class KafkaToHbase {
    public static void main(String[] args) {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE kafka_source (\n" +
				"    `user_id` BIGINT,\n"+
				"    `name` STRING,\n" +
				"    `gender` STRING,\n" +
				"    `tel` STRING,\n" +
				"    `tags` ARRAY<STRING>,\n" +
				"    `comments` ROW<zhangsan STRING,lisi STRING>,\n" +
				"    `dateTime` STRING\n" +
				") WITH (\n" +
				"    'connector' = 'kafka', -- 使用 kafka connector\n" +
				"    'topic' = 'test-zjj-flink-source',  -- kafka topic\n" +
				"    'properties.bootstrap.servers' = '10.12.40.1:9092,10.12.40.2:9092,10.12.40.3:9092',\n" +
				"    'properties.group.id' = 'KafkaToES-1001',\n" +
				"    'scan.startup.mode' = 'latest-offset',  -- 读取数据的位置\n" +
				"    'format' = 'json',  -- 数据源格式为 json\n" +
				"    'json.fail-on-missing-field' = 'true', -- 字段丢失任务不失败\n" +
				"    'json.ignore-parse-errors' = 'false'  -- 解析失败跳过\n" +
				")";

		String ddlSink = "CREATE TABLE hTable (\n" +
				" rowkey BIGINT,\n" +
				" info ROW<name STRING,gender STRING,tel STRING,dateTime STRING>\n" +
				") WITH (\n" +
				" 'connector' = 'hbase-1.4',\n" +
				" 'table-name' = 'mytable',\n" +
				" 'zookeeper.quorum' = '10.12.40.7:2181,10.12.40.8:2181,10.12.40.9:2181',\n" +
				" 'zookeeper.znode.parent' = '/hbase'\n"+
				")";

		String sql = "insert into hTable\n" +
				"select\n" +
				"user_id,\n" +
				"ROW(name,gender,tel,dateTime)\n" +
				"from kafka_source";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		TableResult tableResult = tableEnv.executeSql(sql);
		tableResult.print();

	}
}
