package com.test.springboot;

import com.test.springboot.pojo.Employee;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/19
 */
@SpringBootTest
public class EmployeeUtilTest {
    @Test
    public void testEmployeeUtil1() throws IOException {
        EmployeeUtil employeeUtil = new EmployeeUtil();
        employeeUtil.testJsonToObject();
    }

    @Test
    public void testEmployeeUtil2() throws IOException {
        EmployeeUtil employeeUtil = new EmployeeUtil();
        employeeUtil.testObjectToJson();
    }

    @Test
    public void testFileInputStream() throws IOException {
        EmployeeUtil employeeUtil = new EmployeeUtil();
        employeeUtil.testFileInputStream();

    }
}
