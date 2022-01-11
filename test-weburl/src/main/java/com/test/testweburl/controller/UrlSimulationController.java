package com.test.testweburl.controller;

import com.test.testweburl.pojo.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 模拟itil事件的接口
 *
 * @author: Junjie Zhang
 * @date: 2021/10/9
 */
@RestController
@RequestMapping("/api")
public class UrlSimulationController {

	private static final Logger logger = LoggerFactory.getLogger(UrlSimulationController.class);

	private static final String FILE_PATH_INCIDENT = "incident.txt";
	private static final String FILE_PATH_CHANGE = "change.txt";

	@PostMapping("/Flow/BaseFlowSyncData")
	public String getDataSource(@RequestBody Table table) {
		logger.info(table.toString());
		String filePath = null;
		if ("61".equals(table.getTableID())){
			filePath = FILE_PATH_INCIDENT;
		}else if ("66".equals(table.getTableID())){
            filePath = FILE_PATH_CHANGE;
		}else{
			return null;
		}

		StringBuilder result = new StringBuilder();
		try {
			InputStream inputStream;
			System.out.println("开始读取文件,文件路径为" + filePath);
			inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			System.out.println("结束读取文件,文件路径为" + filePath);
			String str;

			while ((str = bufferedReader.readLine()) != null) {
				result.append(str);
			}
			inputStream.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			logger.error("找不到文件,文件路径为" + filePath);
		} catch (IOException e) {
			logger.error("IO异常,读取文件错误,文件路径为" + filePath);
		}
		return result.toString();
	}

}
