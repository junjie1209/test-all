package com.test.flink.sql.stream.job;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * Kakfa To Kafka
 *
 * @author: Junjie Zhang
 * @date: 2021/9/14
 */
public class KafkaToKafka {

	public static void main(String[] args) throws Exception {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE kafka_source (\n" +
				"    `a` STRING,\n" +
				"    `b` STRING,\n" +
				"    `c` STRING,\n" +
				"    `d` STRING,\n" +
				"    `e` STRING,\n" +
				"    `f` STRING,\n" +
				"    `g` STRING,\n" +
				"    `h` STRING\n" +
				") WITH (\n" +
				"    'connector' = 'kafka',\n" +
					"    'topic' = 'test-zjj-101',\n" +
				"    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
				"    'properties.group.id' = '1001',\n" +
				"    'scan.startup.mode' = 'latest-offset',\n" +
				"    'format' = 'json',\n" +
				"    'json.fail-on-missing-field' = 'true',\n" +
				"    'json.ignore-parse-errors' = 'false'\n" +
				")";

		String ddlSink = "CREATE TABLE kafka_sink (\n" +
				"    `measures` ROW<`a` STRING,`b` STRING>,\n" +
				"    `dimensions` ROW<`c` STRING,`d` STRING,`e` STRING>,\n" +
				"    `normalFields` ROW<`f` STRING,`g` STRING,`h` STRING>\n" +
				") WITH (\n" +
				"    'connector' = 'kafka',\n" +
				"    'topic' = 'test-zjj-102',\n" +
				"    'properties.bootstrap.servers' = '10.12.40.1:9092',\n" +
				"    'format' = 'json'\n" +
				")";

		String sql = "insert into kafka_sink\n" +
				"select\n" +
				"Row(a,b),\n" +
				"Row(c,d,e),\n" +
				"Row(f,g,e)\n" +
				"from kafka_source";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		tableEnv.executeSql(sql);
	}

}
