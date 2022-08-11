package com.test.compare.with.python;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/4/1
 */
public class CompareWithList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhangSan", "liSi", "wanWu");
        list.set(0,"sb");
        System.out.println(list);


    }
}
