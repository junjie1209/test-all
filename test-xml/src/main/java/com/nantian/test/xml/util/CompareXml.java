package com.nantian.test.xml.util;



import org.springframework.util.Assert;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEngine;

import javax.xml.transform.Source;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/14
 */
public class CompareXml{
    public static void assertXMLEquals(String path1, String path2) throws Exception {
        Source source1 = Input.fromFile(path1).build();
        Source source2 = Input.fromFile(path2).build();
        /*DifferenceEngine diff = new DOMDifferenceEngine();
        diff.compare(source1,source2);*/

        Diff myDiff = DiffBuilder.compare(source1)
                .withTest(source2)
                .build();
        Assert.isTrue(myDiff.hasDifferences(), myDiff.toString());
    }

}
