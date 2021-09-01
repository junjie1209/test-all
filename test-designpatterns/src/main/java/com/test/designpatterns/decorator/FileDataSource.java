package com.test.designpatterns.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public class FileDataSource implements DataSource {

	private static final Logger logger = LoggerFactory.getLogger(FileDataSource.class);

	private String name;

	public FileDataSource(String name) {
		this.name = name;
	}

	@Override
	public void writeData(String data) {
		File file = new File(name);
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			fileOutputStream.write(data.getBytes(), 0, data.length());
		} catch (IOException e) {
			logger.error("文件写入失败", e);
		}
	}

	@Override
	public String readData() {
		File file = new File(name);
		char[] buff = null;
		try (FileReader fileReader = new FileReader(file)) {
			buff = new char[(int) file.length()];
			fileReader.read(buff);
		} catch (IOException e) {
			logger.error("文件读取失败", e);
		}
		return new String(buff);
	}

}
