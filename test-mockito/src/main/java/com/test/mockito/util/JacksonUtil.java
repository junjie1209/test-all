package com.test.mockito.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mockito.pojo.Student;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
public class JacksonUtil {
    public String transformObject2String() throws JsonProcessingException {
        Student student = new Student();
        student.setStudentId(1001L);
        student.setName("zhangSan");
        student.setAge(18L);
        student.setDescribe("第一帅");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
}
