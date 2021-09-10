package com.test.flink.sql.stream.connection.jdbc.mysql;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/1
 */
public class FromMysql {

	public static void main(String[] args) {
		EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.inStreamingMode()
				.build();
		TableEnvironment tableEnv = TableEnvironment.create(settings);

		String ddlSource = "CREATE TABLE MyUserTable (\n" +
				"  id BIGINT,\n" +
				"  name STRING,\n" +
				"  age INT,\n" +
				"  status INT,\n" +
				"  PRIMARY KEY (id) NOT ENFORCED\n" +
				") WITH (\n" +
				"   'connector' = 'jdbc',\n" +
				"   'driver' = 'com.mysql.cj.jdbc.Driver',\n" +
				"   'url' = 'jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull',\n" +
				"   'table-name' = 'users',\n" +
				"   'username' = 'root',\n" +
				"   'password' = '123456'\n" +
				")";

		String sql = "SELECT id, name, age, status FROM MyUserTable";

		tableEnv.executeSql(ddlSource);
		TableResult tableResult = tableEnv.executeSql(sql);
		tableResult.print();
	}

}
