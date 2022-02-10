package com.test.hikari.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 测试hikari
 *
 * @author: Junjie Zhang
 * @date: 2022/1/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Long age;
    @Column(name = "describe")
    private String describe;

}
