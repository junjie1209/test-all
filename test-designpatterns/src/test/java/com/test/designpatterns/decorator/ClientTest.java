package com.test.designpatterns.decorator;

import org.junit.jupiter.api.Test;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/19
 */
public class ClientTest {

	@Test
	public void testFileDataSource() {
		String studentInformation = "my name is zhangJJ";
		FileDataSource fileDataSource = new FileDataSource("D:\\test\\test-design-patterns\\a.txt");
		fileDataSource.writeData(studentInformation);
		System.out.println(fileDataSource.readData());
	}

	@Test
	public void testEncryptionDecorator() {
		String studentInformation = "my name is zhangJJ dfdfd dfdfdfdf" +
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"+
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"+
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"
				+"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss";
		FileDataSource fileDataSource = new FileDataSource("D:\\test\\test-design-patterns\\b.txt");
		EncryptionDecorator encryptionDecorator = new EncryptionDecorator(fileDataSource);
		encryptionDecorator.writeData(studentInformation);
		System.out.println(encryptionDecorator.readData());
	}

	@Test
	public void testCompressionDecorator(){
		String studentInformation = "my name is zhangJJ dfdfd dfdfdfdf" +
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"+
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"+
				"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss"
				+"fdfsdsdsdsdsdsdsdsddddddddddddddddddddddddddd" +
				"dfsdfsdffffffffdsdsdsdssdsaddsdsdsdsfffffffffffffffffff" +
				"fsdfdsfdsfdsfdssdsdsdsdsdsdsdsssssssssssssssss" +
				"fdsfdsfdsfdsfdsdsdsdsdsdsdsdsdsdsdssssssssssssssssssssss";

		DataSourceDecorator encoded = new CompressionDecorator(
				new EncryptionDecorator(
						new FileDataSource("D:\\test\\test-design-patterns\\c.txt")));
		encoded.writeData(studentInformation);

		System.out.println(encoded.readData());
	}

}
