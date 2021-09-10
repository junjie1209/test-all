package com.test.flink.sql.stream.connection.hbase;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * Desc: Blink Stream SQL Job, 读取 Kafka 数据，然后写入到 HBase
 * Created by zhisheng on 2019/11/3 下午1:14
 * blog：http://www.54tianzhisheng.cn/
 * 微信公众号：zhisheng
 */
public class SQLExampleKafkaData2HBase {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment blinkStreamEnv = StreamExecutionEnvironment.getExecutionEnvironment();
		blinkStreamEnv.setParallelism(1);
		EnvironmentSettings blinkStreamSettings = EnvironmentSettings.newInstance()
				.useBlinkPlanner()
				.inStreamingMode()
				.build();
		StreamTableEnvironment blinkStreamTableEnv = StreamTableEnvironment.create(blinkStreamEnv, blinkStreamSettings);

		String ddlSource = "CREATE TABLE user_behavior (\n" +
				"    user_id BIGINT,\n" +
				"    item_id BIGINT,\n" +
				"    category_id BIGINT,\n" +
				"    behavior STRING\n" +
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

		String ddlSink = "CREATE TABLE user_behavior_hbase (\n" +
				"  user_id BIGINT,\n" +
				"  cf ROW<item_id BIGINT, category_id BIGINT>\n" +
				") WITH (\n" +
				"  'connector' = 'hbase-1.4',\n" +
				"  'table-name' = 'zhisheng01',\n" +
				"  'zookeeper.quorum' = '10.12.40.7:2181',\n" +
				"  'zookeeper.znode.parent' = '/hbase'\n" +
				")";

		// 提取读取到的数据，然后只要两个字段，写入到 HBase
		String sql = "insert into user_behavior_hbase select user_id, ROW(item_id, category_id) from user_behavior";

		blinkStreamTableEnv.executeSql(ddlSource).print();
		blinkStreamTableEnv.executeSql(ddlSink).print();
		blinkStreamTableEnv.executeSql(sql).print();

	}

}
