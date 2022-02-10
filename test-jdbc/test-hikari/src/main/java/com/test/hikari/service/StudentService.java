package com.test.hikari.service;

import com.test.hikari.dao.StudentDao;
import com.test.hikari.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
public interface StudentService {
    Student insert(Student student);

    List<Student> getAll();


}
