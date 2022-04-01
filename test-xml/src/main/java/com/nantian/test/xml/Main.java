package com.nantian.test.xml;

import com.nantian.test.xml.util.CompareXml;

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
public class Main {
    public static void main(String[] args) throws Exception {
        String path1 = "D:\\Files\\网络自动化\\测试xml\\diff1.xml";
        String path2 = "D:\\Files\\网络自动化\\测试xml\\diff2.xml";
        CompareXml.assertXMLEquals(path1,path2);
    }
}
