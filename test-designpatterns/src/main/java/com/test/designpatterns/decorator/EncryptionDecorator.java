package com.test.designpatterns.decorator;

import java.util.Base64;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public class EncryptionDecorator extends DataSourceDecorator {

	public EncryptionDecorator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void writeData(String data) {
		super.writeData(encode(data));
	}

	@Override
	public String readData() {
		return decode(super.readData());
	}

	private String encode(String data) {
		byte[] result = data.getBytes();
		for (int i = 0; i < result.length; i++) {
			result[i] += (byte) 1;
		}
		return Base64.getEncoder().encodeToString(result);
	}

	private String decode(String data) {
		byte[] result = Base64.getDecoder().decode(data);
		for (int i = 0; i < result.length; i++) {
			result[i] -= (byte) 1;
		}
		return new String(result);
	}

}
