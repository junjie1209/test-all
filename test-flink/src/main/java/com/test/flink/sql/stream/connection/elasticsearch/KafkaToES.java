package com.test.flink.sql.stream.connection.elasticsearch;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/6
 */
public class KafkaToES {

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
				"    'properties.group.id' = 'KafkaToES-1001',\n" +
				"    'scan.startup.mode' = 'latest-offset',  -- 读取数据的位置\n" +
				"    'format' = 'json',  -- 数据源格式为 json\n" +
				"    'json.fail-on-missing-field' = 'true', -- 字段丢失任务不失败\n" +
				"    'json.ignore-parse-errors' = 'false'  -- 解析失败跳过\n" +
				")";

		String ddlSink = "CREATE TABLE es_sink (\n" +
				"    `name` STRING,\n" +
				"    `gender` STRING,\n" +
				"    `tel` STRING,\n" +
				"    `tags` STRING,\n" +
				"    `comment-zhangsan` STRING,\n" +
				"    `comment-lisi` STRING,\n" +
				"    `dateTime` STRING\n" +
				") WITH (\n" +
				"  'connector' = 'elasticsearch-6',\n" +
				"  'hosts' = 'http://10.12.40.10:9200',\n" +
				"  'index' = 'zjj-test-index-01',\n" +
				"  'document-type' = 'TEST',\n"+
				"  'format' = 'json'\n" +
				")";

		String sql = "insert into es_sink\n" +
				"select\n" +
				"name,\n" +
				"gender,\n" +
				"tel,\n" +
				"CONCAT(tags[1],',',tags[2],',',tags[3]),\n" +
				"comments.zhangsan,\n" +
				"comments.lisi,\n" +
				"dateTime\n" +
				"from kafka_source";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		tableEnv.executeSql(sql);
	}

}
