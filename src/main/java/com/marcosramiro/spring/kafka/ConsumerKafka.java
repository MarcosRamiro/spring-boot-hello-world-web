package com.marcosramiro.spring.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcosramiro.spring.config.AppConfig;

public class ConsumerKafka {

	private static Logger LOGGER = LoggerFactory.getLogger(ConsumerKafka.class);
	
	public static void main(String[] args) {
		
		String topic = "first_topic";
		
		KafkaConsumer<String,String> consumer = getKafkaConsumer();
		
		consumer.subscribe(Collections.singleton(topic));
		
		while(true) {
			
			ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(5000));
			
			for(ConsumerRecord<String,String> record : records) {
				LOGGER.info("Key: {}, Value: {}", record.key(), record.value());
				LOGGER.info("Partition: {}, Offset: {}", record.partition(), record.offset());
			}
		}
	}
	
	private static KafkaConsumer<String, String> getKafkaConsumer() {

		Properties config = obterKafkaConsumerProperties();

		return new KafkaConsumer<>(config);

	}
	
	private static Properties obterKafkaConsumerProperties() {

		Properties config = new Properties();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class.getName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-web");
		
		return config;
	}
}
