package com.test.flink.sql.stream.job;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

import com.test.flink.sql.stream.job.udf.MapToStringUdf;

/**
 * Kakfa json To Kafka csv
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class KafkaJsonToKafkaCsv {

	public static void main(String[] args) throws Exception {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		tableEnv.createFunction("mapToStringUdf", MapToStringUdf.class);

		String ddlSource = "CREATE TABLE kafka_source (\n" +
				"    `name` STRING,\n" +
				"    `tel` STRING,\n" +
				"    `age` BIGINT,\n" +
				"    `pay` DECIMAL(10,2),\n" +
				"    `date` STRING,\n" +
				"    `tags` ARRAY<STRING>,\n" +
				"    `comments` MAP<STRING,STRING>,\n" +
				"    `lover` ROW<name STRING,gender STRING>\n" +
				") WITH (\n" +
				"    'connector' = 'kafka',\n" +
				"    'topic' = 'test-zjj-flink-job1-kafkaJson',\n" +
				"    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
				"    'properties.group.id' = '1001',\n" +
				"    'scan.startup.mode' = 'latest-offset',\n" +
				"    'format' = 'json',\n" +
				"    'json.fail-on-missing-field' = 'true',\n" +
				"    'json.ignore-parse-errors' = 'false'\n" +
				")";

		String ddlSink = "CREATE TABLE kafka_sink (\n" +
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
				"    'format' = 'csv'\n" +
				")";

		String sql = "insert into kafka_sink\n" +
				"select\n" +
				"name,\n" +
				"tel,\n" +
				"age,\n" +
				"pay,\n" +
				"`date`,\n" +
				"CONCAT(tags[1],',',tags[2],',',tags[3]),\n" +
				"mapToStringUdf(comments),\n	" +
				"lover.name,\n" +
				"lover.gender\n" +
				"from kafka_source";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		tableEnv.executeSql(sql);
	}

}
