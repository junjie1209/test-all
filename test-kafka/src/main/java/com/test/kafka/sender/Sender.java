package com.test.kafka.sender;

import com.alibaba.fastjson.JSONObject;
import com.test.kafka.model.Student;
import com.test.kafka.tool.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Sender implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Sender.class);

	private static final List<Student> students;

	static {
		students = new ArrayList<Student>();
		students.add(new Student(1001, "zhangsan", 18));
		students.add(new Student(1002, "lisi", 19));
		students.add(new Student(1003,"wangwu",20));
		students.add(new Student(1004,"zhaosi",21));
	}

	private String topic = "demo1-simulation-data";
	//private Long intervalTime = 10000L;
	private Long intervalTime = 0L;

	private static KafkaSender kafkaSender = new KafkaSender();

	@Override
	public void run() {
			while (true) {
				sendCurrentDataToKafka();
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e) {
					logger.error("Thread.sleep方法异常", e);
				}
			}
	}

	public void sendCurrentDataToKafka() {
		for (int i = 0; i < students.size(); i++) {
			Student student = students.get(i);
			String msg = JSONObject.toJSONString(student);
			kafkaSender.send(topic, msg);
			System.out.println("post to kafka topic(" + topic + ")->" + msg);
		}
	}
}
