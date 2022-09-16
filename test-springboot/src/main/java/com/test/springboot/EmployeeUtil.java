/*
package com.test.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.ior.ObjectKey;
import com.test.springboot.pojo.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

*/
/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/1/19
 *//*

public class EmployeeUtil {

    public void testJsonToObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(new File(this.getClass().getResource("/data.json").getPath()), Employee.class);
        System.out.println(employee.toString());
    }

    public void testObjectToJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = this.getClass().getResource("/data.json").getPath();
        Employee employee = objectMapper.readValue(new File(sourcePath), Employee.class);
        System.out.println(sourcePath);
        System.out.println(employee.toString());
        String sinkPath = this.getClass().getResource("/data1.json").getPath();
        objectMapper.writeValue(new File(sinkPath),employee);
        //objectMapper.writeValue(System.out,employee);
    }

    public void testFileInputStream() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.getClass().getResource("/data.json").getPath();
        Employee employee = objectMapper.readValue(new FileInputStream(path), Employee.class);
        System.out.println(employee.toString());
    }
}
*/
