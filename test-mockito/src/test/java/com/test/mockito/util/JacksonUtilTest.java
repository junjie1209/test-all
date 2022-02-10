package com.test.mockito.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.mockito.pojo.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
public class JacksonUtilTest {
    private JacksonUtil jacksonUtil;

    @BeforeEach
    public void init() throws JsonProcessingException {
        jacksonUtil = Mockito.mock(JacksonUtil.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(jacksonUtil.transformObject2String()).thenReturn("ds");
    }

    @Test
    public void testTransformObject2String() throws JsonProcessingException {
        Student student = new Student();
        Assertions.assertEquals("ds", jacksonUtil.transformObject2String());
    }

}
