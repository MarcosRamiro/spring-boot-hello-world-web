package com.marcosramiro.spring.config;

import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marcosramiro.spring.handler.LogProxyHandler;
import com.marcosramiro.spring.service.PIService;
import com.marcosramiro.spring.service.impl.PIServiceImpl;

@Configuration
public class AppConfig {

	private static Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public PIService getPIService() {

		LOGGER.info("Create Bean: PIService");

		PIService proxyInstance = (PIService) Proxy.newProxyInstance(PIService.class.getClassLoader(),
				new Class[] { PIService.class }, new LogProxyHandler(new PIServiceImpl()));

		return proxyInstance;
	}

}
