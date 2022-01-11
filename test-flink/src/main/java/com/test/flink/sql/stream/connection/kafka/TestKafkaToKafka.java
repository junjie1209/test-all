package com.test.flink.sql.stream.connection.kafka;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

/**
 * flink sql通过sql在source表和sink表之间进行转换
 *
 * @author: Junjie Zhang
 * @date: 2021/9/1
 */
public class TestKafkaToKafka {

	public static void main(String[] args) throws Exception {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE IF NOT EXISTS ods_bk_monitor_and_iknow_order(\n" +
				"`Statechange` STRING,\n" +
				"`KPIName` STRING,\n" +
				"`IP` STRING ,\n" +
				"`KPIUnit` STRING,\n" +
				"`SystemName` STRING,\n" +
				"`CIID` STRING,\n" +
				"`CIType` STRING,\n" +
				"`HostName` STRING,\n" +
				"`SystemID` STRING,\n" +
				"`KPIID` STRING,\n" +
				"`MonitorTools` STRING,\n" +
				"`KPIValue` STRING,\n" +
				"`SubCI` STRING,\n" +
				"`CIType_CMDB` STRING,\n" +
				"`CIID_CMDB` STRING\n" +
				") WITH(\n" +
				"'connector' = 'kafka',\n" +
				"'topic' = 'HXB_Monitor_OperatingSystem',\n" +
				"'properties.bootstrap.servers' = '10.12.40.40:9092,10.12.40.41:9092,10.12.40.42:9092',\n" +
				"'properties.group.id' = '1002',\n" +
				"'scan.startup.mode' = 'latest-offset',\n" +
				"'format' = 'json',\n" +
				"'json.fail-on-missing-field' = 'false',\n" +
				"'json.ignore-parse-errors' = 'false'\n" +
				")";

		String ddlSink = "CREATE TABLE IF NOT EXISTS dwd_bk_monitor_and_iknow_order(\n" +
				"`class_id` STRING,\n" +
				"`instance_id` STRING,\n" +
				"`statechange` STRING,\n" +
				"`kpi_name` STRING,\n" +
				"`ip` STRING ,\n" +
				"`kpi_unit` STRING,\n" +
				"`system_name` STRING,\n" +
				"`ci_id` STRING,\n" +
				"`ci_type` STRING,\n" +
				"`host_name` STRING,\n" +
				"`system_id` STRING,\n" +
				"`kpi_id` STRING,\n" +
				"`monitor_tools` STRING,\n" +
				"`kpi_value` STRING,\n" +
				"`sub_ci` STRING,\n" +
				"`ci_type_cmdb` STRING,\n" +
				"`ci_id_cmdb` STRING\n" +
				") WITH(\n" +
				"'connector' = 'kafka',\n" +
				"'topic' = 'Zjj_HXB_Monitor_OperatingSystem_Dwd',\n" +
				"'properties.bootstrap.servers' = '10.12.40.40:9092,10.12.40.41:9092,10.12.40.42:9092',\n" +
				"'properties.group.id' = '1001',\n" +
				"'scan.startup.mode' = 'latest-offset',\n" +
				"'format' = 'json',\n" +
				"'json.fail-on-missing-field' = 'false',\n" +
				"'json.ignore-parse-errors' = 'false'\n" +
				")";

		String sql = "INSERT INTO dwd_bk_monitor_and_iknow_order\n" +
				"SELECT\n" +
				"'123456',\n" +
				"'LogicalServer',\n" +
				"IFNULL(`Statechange`,'unknown'),\n" +
				"IFNULL(`KPIName`,'unknown'),\n" +
				"IFNULL(`IP`,'unknown'),\n" +
				"IFNULL(`KPIUnit`,'unknown'),\n" +
				"IFNULL(`SystemName`,'unknown'),\n" +
				"IFNULL(`CIID`,'unknown'),\n" +
				"IFNULL(`CIType`,'unknown'),\n" +
				"IFNULL(`HostName`,'unknown'),\n" +
				"IFNULL(`SystemID`,'unknown'),\n" +
				"IFNULL(`KPIID`,'unknown'),\n" +
				"IFNULL(`MonitorTools`,'unknown'),\n" +
				"IFNULL(`KPIValue`,'unknown'),\n" +
				"IFNULL(`SubCI`,'unknown'),\n" +
				"IFNULL(`CIType_CMDB`,'unknown'),\n" +
				"IFNULL(`CIID_CMDB`,'unknown')\n" +
				"FROM ods_bk_monitor_and_iknow_order";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		tableEnv.executeSql(sql);
	}

}
