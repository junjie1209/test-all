package com.test.kettle;

import org.junit.jupiter.api.Test;
import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransExecutionConfiguration;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.www.SlaveServerTransStatus;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/10/29
 */
public class Main {

    @Test
    public void run(){
    String jobPath = "D:\\test\\test-dc-kettle\\etl_source2stg.ktr";
        try {
            StepPluginType.getInstance().getPluginFolders().
                    add(new PluginFolder("D:\\coding\\tools\\data-integration-9.2\\plugins", false, true));
        KettleEnvironment.init();
        SlaveServer slaveServer = new SlaveServer();
        slaveServer.setHostname("10.12.40.4");
        slaveServer.setPort("8090");
        slaveServer.setUsername("cluster");
        slaveServer.setPassword("cluster");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("etl_source2stg.ktr");
        TransMeta transMeta = new TransMeta(inputStream, null, true, null, null);

        //TransMeta transMeta = new TransMeta(jobPath,"test-junjie");
        TransExecutionConfiguration transExecutionConfiguration = new TransExecutionConfiguration();
        Map<String,String> map = new HashMap<>();
        map.put("mysql.dbName","test");
        map.put("mysql.host","10.12.40.38");
        map.put("mysql.password","Galaxy_123");
        map.put("mysql.port","3306");
        map.put("mysql.username","root");
        map.put("source.url","http://10.12.40.4:8080/api/Flow/BaseFlowSyncData");
        transExecutionConfiguration.setParams(map);
        transExecutionConfiguration.setRemoteServer(slaveServer);// 配置远程服务
        String lastCarteObjectId  = Trans.sendToSlaveServer(transMeta, transExecutionConfiguration, null, null);
        System.out.println("lastCarteObjectId=" + lastCarteObjectId);
        SlaveServerTransStatus transStatus = null;

        do {
            Thread.sleep(5000);
            transStatus = slaveServer.getTransStatus(transMeta.getName(), lastCarteObjectId, 0);
        } while (transStatus != null && transStatus.isRunning());
        Result oneResult = new Result();
        System.out.println(transStatus);
        if (transStatus.getResult() != null) {
            // 流程完成，得到结果
            oneResult = transStatus.getResult();
            System.out.println("Result:" + oneResult);
        } else {
            System.out.println("取到空了");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    }

}
