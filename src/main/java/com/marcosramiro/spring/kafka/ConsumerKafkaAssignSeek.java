package com.marcosramiro.spring.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerKafkaAssignSeek {

	private static Logger LOGGER = LoggerFactory.getLogger(ConsumerKafkaAssignSeek.class);
	
	public static void main(String[] args) {
		
		String topic = "first_topic";
		
		KafkaConsumer<String,String> consumer = getKafkaConsumer();
		
		//assign
		TopicPartition partitionToRead = new TopicPartition(topic, 0);
		long offsetToReadFrom = 15L;
		consumer.assign(Arrays.asList(partitionToRead));
		
		//seek
		consumer.seek(partitionToRead, offsetToReadFrom);
		
		int numberOfMessagesToRead = 5;
		int numberOfMessagesToReadSoFar = 0;
		boolean keepOnReading = true;
		
		while(keepOnReading) {
			
			ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(5000));
			
			for(ConsumerRecord<String,String> record : records) {
			
				logMessage(record);
				numberOfMessagesToReadSoFar++;
				keepOnReading = keepOnReading(numberOfMessagesToReadSoFar, numberOfMessagesToRead);
				if(!keepOnReading) break;

			}
		}
		
		LOGGER.info("Exiting the application");
		
	}


	private static void logMessage(ConsumerRecord<String, String> record) {
		LOGGER.info(" ** Log Messagem ** \n" +
					"	Key: {}, Value: {} \n" +
					"	Partition: {}, Offset: {}"
							, record.key(), record.value(), record.partition(), record.offset());
	}
	

	private static KafkaConsumer<String, String> getKafkaConsumer() {

		Properties config = getProperties();

		return new KafkaConsumer<>(config);

	}
	
	private static Properties getProperties() {

		Properties config = new Properties();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class.getName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		return config;
	}
	
	private static boolean keepOnReading(int numberOfMessagesToReadSoFar, int numberOfMessagesToRead) {
		return numberOfMessagesToReadSoFar < numberOfMessagesToRead;
	}
}
