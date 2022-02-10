package com.test.hikari.service.impl;

import com.test.hikari.dao.StudentDao;
import com.test.hikari.pojo.Student;
import com.test.hikari.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public Student insert(Student student) {
        Student studentSaveResult = studentDao.save(student);
        return studentSaveResult;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = studentDao.findAll();
        return students;
    }
}
