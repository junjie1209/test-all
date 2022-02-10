package com.test.ioc.service.impl;

import com.test.ioc.pojo.Student;
import com.test.ioc.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/25
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Student getStudent() {
        Student student = new Student();
        student.setStudentId("1001");
        student.setName("zhangSan");
        student.setAge(18L);
        student.setDescribe("天下第一帅");

        return student;
    }
}
