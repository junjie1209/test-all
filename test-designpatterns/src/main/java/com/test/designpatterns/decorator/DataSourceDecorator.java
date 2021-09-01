package com.test.designpatterns.decorator;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public class DataSourceDecorator implements DataSource {

	private final DataSource wrapper;

	DataSourceDecorator(DataSource dataSource) {
		this.wrapper = dataSource;
	}

	@Override
	public void writeData(String data) {
		wrapper.writeData(data);
	}

	@Override
	public String readData() {
		return wrapper.readData();
	}

}
