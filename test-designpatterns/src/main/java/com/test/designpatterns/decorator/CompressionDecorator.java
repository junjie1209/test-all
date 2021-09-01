package com.test.designpatterns.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public class CompressionDecorator extends DataSourceDecorator {

	private static final Logger logger = LoggerFactory.getLogger(CompressionDecorator.class);

	private int compressionLevel = 6;

	public CompressionDecorator(DataSource dataSource) {
		super(dataSource);
	}

	public int getCompressionLevel() {
		return compressionLevel;
	}

	public void setCompressionLevel(int value) {
		compressionLevel = value;
	}

	@Override
	public void writeData(String data) {
		super.writeData(compress(data));
	}

	@Override
	public String readData() {
		return decompress(super.readData());
	}

	private String compress(String stringData) {
		byte[] data = stringData.getBytes();
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
			DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compressionLevel));
			dos.write(data);
			dos.close();
			bout.close();
			return Base64.getEncoder().encodeToString(bout.toByteArray());
		} catch (IOException e) {
			logger.error("文件压缩失败", e);
			return null;
		}
	}

	private String decompress(String stringData) {
		byte[] data = Base64.getDecoder().decode(stringData);
		int b;
		try {
			InputStream in = new ByteArrayInputStream(data);
			InflaterInputStream iin = new InflaterInputStream(in);
			ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
			while ((b = iin.read()) != -1) {
				bout.write(b);
			}
			in.close();
			iin.close();
			bout.close();
			return new String(bout.toByteArray());
		} catch (IOException e) {
			logger.error("文件解压缩失败", e);
			return null;
		}
	}

}
