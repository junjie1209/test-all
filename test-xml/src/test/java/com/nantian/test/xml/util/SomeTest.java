package com.nantian.test.xml.util;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/14
 */
/*
public class SomeTest extends XMLTestCase {
    @Test
    public void test1() throws IOException, SAXException {
        byte[] bytes1 = Files.readAllBytes(Paths.get("D:\\Files\\网络自动化\\测试xml\\diff1.xml"));
        String xml1 = new String(bytes1, StandardCharsets.UTF_8);

        byte[] bytes2 = Files.readAllBytes(Paths.get("D:\\Files\\网络自动化\\测试xml\\diff2.xml"));
        String xml2 = new String(bytes2, StandardCharsets.UTF_8);
        // 忽略空格
        XMLUnit.setIgnoreWhitespace(true);
        // 忽略标签顺序
        XMLUnit.setIgnoreAttributeOrder(false);
        assertXMLEqual(xml1, xml2);
    }
}
*/
