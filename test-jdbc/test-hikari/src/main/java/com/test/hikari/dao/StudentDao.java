package com.test.hikari.dao;

import com.test.hikari.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
@Repository
public interface StudentDao extends JpaRepository<Student,Long> {
}
