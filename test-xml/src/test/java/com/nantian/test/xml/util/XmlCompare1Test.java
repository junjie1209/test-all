package com.nantian.test.xml.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/15
 */
public class XmlCompare1Test {

    @Test
    public void test1() {
        final String control = "<a><b attr=\"abc\"></b></a>";
        final String test = "<a><b attr=\"xyz\"></b></a>";

        Diff myDiff = DiffBuilder.compare(Input.fromString(control))
                .withTest(Input.fromString(test))
                .build();

        Assert.assertFalse(myDiff.toString(), myDiff.hasDifferences());
    }
}
