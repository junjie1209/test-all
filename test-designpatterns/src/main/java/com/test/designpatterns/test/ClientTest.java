package com.test.designpatterns.test;

import com.test.designpatterns.decorator.CompressionDecorator;
import com.test.designpatterns.decorator.DataSourceDecorator;
import com.test.designpatterns.decorator.EncryptionDecorator;
import com.test.designpatterns.decorator.FileDataSource;

/**
 * @description TODO
 * @author: Junjie Zhang
 * @date: 2021/8/20
 */
public class ClientTest {
    public static void main(String[] args) {
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
                        new FileDataSource("D\\test\\test-design-patterns\\c.txt")));
        encoded.writeData(studentInformation);

        System.out.println(encoded.readData());
    }
}
