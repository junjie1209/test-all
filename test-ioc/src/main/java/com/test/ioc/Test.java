package com.test.ioc;

import com.test.ioc.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/26
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);

    }
}
