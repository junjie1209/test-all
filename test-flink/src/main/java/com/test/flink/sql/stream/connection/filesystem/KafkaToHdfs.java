package com.test.flink.sql.stream.connection.filesystem;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/8
 */
public class KafkaToHdfs {

	public static void main(String[] args) {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE kafka_source (\n" +
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
				"    'properties.group.id' = '1001',\n" +
				"    'scan.startup.mode' = 'latest-offset',  -- 读取数据的位置\n" +
				"    'format' = 'json',  -- 数据源格式为 json\n" +
				"    'json.fail-on-missing-field' = 'true', -- 字段丢失任务不失败\n" +
				"    'json.ignore-parse-errors' = 'false'  -- 解析失败跳过\n" +
				")";

		String ddlSink = "CREATE TABLE windows_sink (\n" +
				"  name STRING,\n" +
				"  gender STRING,\n" +
				"  tel STRING,\n" +
				"  dateTime STRING\n" +
				") WITH (\n" +
				"  'connector.type' = 'filesystem',\n" +
				"  'connector.path' = 'hdfs://10.12.40.7:50010/flink-test',\n" +
				"  'format.type' = 'csv'\n" +
				")";

		String sql = "INSERT INTO windows_sink \n" +
				"SELECT \n" +
				"    name,\n" +
				"    gender,\n" +
				"    tel,\n" +
				"    dateTime\n" +
				"FROM kafka_source";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		tableEnv.executeSql(sql).print();
	}

}
