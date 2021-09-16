package com.test.flink.sql.stream.connection.hive;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.catalog.hive.HiveCatalog;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/9/9
 */
public class Example {
    public static void main(String[] args) {
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);

        String name            = "myhive";
        String defaultDatabase = "default";
        String hiveConfDir     = "test-all/test-flink/src/main/resources/";

        HiveCatalog hive = new HiveCatalog(name, defaultDatabase, hiveConfDir);
        tableEnv.registerCatalog("myhive", hive);

        // set the HiveCatalog as the current catalog of the session
        tableEnv.useCatalog("myhive");
    }
}
