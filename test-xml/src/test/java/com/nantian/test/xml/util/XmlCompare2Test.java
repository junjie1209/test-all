package com.nantian.test.xml.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

import java.io.File;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/15
 */
public class XmlCompare2Test {

    @Test
    public void test1() {
        Diff myDiff = DiffBuilder.compare(Input.fromFile(new File("D:\\Files\\网络自动化\\测试xml\\diff1.xml")))
                .withTest(Input.fromFile(new File("D:\\Files\\网络自动化\\测试xml\\diff2.xml")))
                // 语义相同
                .checkForSimilar()
                // 忽略注释
                .ignoreComments()
                // 忽略空格
                .ignoreWhitespace()
                // 忽略顺序
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
                .build();

        Assert.assertFalse(myDiff.toString(), myDiff.hasDifferences());
    }
}
