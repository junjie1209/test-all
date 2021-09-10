package com.test.flink.sql.stream.connection.jdbc.mysql;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/6
 */
public class MysqlToMysql {

	public static void main(String[] args) {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE MyUserSourceTable (\n" +
				"  id BIGINT,\n" +
				"  name STRING,\n" +
				"  age INT,\n" +
				"  status INT,\n" +
				"  PRIMARY KEY (id) NOT ENFORCED\n" +
				") WITH (\n" +
				"   'connector' = 'jdbc',\n" +
				"   'driver' = 'com.mysql.cj.jdbc.Driver',\n" +
				"   'url' = 'jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull',\n" +
				"   'table-name' = 'user_source',\n" +
				"   'username' = 'root',\n" +
				"   'password' = '123456'\n" +
				")";

		String ddlSink = "CREATE TABLE MyUserSinkTable (\n" +
				"  id BIGINT,\n" +
				"  name STRING,\n" +
				"  age INT,\n" +
				"  status INT,\n" +
				"  PRIMARY KEY (id) NOT ENFORCED\n" +
				") WITH (\n" +
				"   'connector' = 'jdbc',\n" +
				"   'driver' = 'com.mysql.cj.jdbc.Driver',\n" +
				"   'url' = 'jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull',\n" +
				"   'table-name' = 'user_sink',\n" +
				"   'username' = 'root',\n" +
				"   'password' = '123456'\n" +
				")";

		String sql = "INSERT INTO MyUserSinkTable SELECT id, name, age, status FROM MyUserSourceTable";

		tableEnv.executeSql(ddlSource);
		tableEnv.executeSql(ddlSink);
		TableResult tableResult = tableEnv.executeSql(sql);
	}

}
