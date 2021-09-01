package com.test.designpatterns.decorator;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public interface DataSource {

	void writeData(String data);

	String readData();

}
