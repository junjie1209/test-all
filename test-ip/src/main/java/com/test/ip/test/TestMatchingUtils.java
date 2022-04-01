package com.test.ip.test;

import com.test.ip.util.MatchingUtils;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/3/25
 */
public class TestMatchingUtils {
    public static void main(String[] args) {
        boolean b = MatchingUtils.isInet4NetworkBelongNetwork("10.0.0.1/32", "10.0.1.1/23");
        System.out.println(b);
    }
}
