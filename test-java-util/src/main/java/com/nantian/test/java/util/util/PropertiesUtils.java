package com.nantian.test.java.util.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties getProperty(String fileName) throws IOException {
		Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(fileName));
		return properties;
	}

}
