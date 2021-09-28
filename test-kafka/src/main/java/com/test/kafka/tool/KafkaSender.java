package com.test.kafka.tool;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaSender {

	private KafkaProducer<String, String> producer;

	public KafkaSender() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.12.40.1:9092");
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		this.producer = new KafkaProducer<String, String>(props);
	}

	public void send(String topic, String msg) {
		producer.send(new ProducerRecord<>(topic, msg));
	}

}
