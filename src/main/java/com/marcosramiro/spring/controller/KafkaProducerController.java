package com.marcosramiro.spring.controller;

import java.util.stream.IntStream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

	private static Logger LOGGER = LoggerFactory.getLogger(KafkaProducerController.class);

	@Autowired
	private KafkaProducer<String, String> producer;

	@GetMapping
	public ResponseEntity<String> teste() {

		IntStream.range(0, 10).forEach(this::publicarKafka);

		return ResponseEntity.ok().build();

	}

	private void publicarKafka(int numero)  {

		producer.send(prepararMensagem(numero), this::callback);
		
	}

	private ProducerRecord<String, String> prepararMensagem(int i) {

		String topic = "first_topic";
		String key = "id_" + Integer.toString(i);
		String value = "minha primeira msg " + Integer.toString(i);

		ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

		return record;

	}

	public void callback(RecordMetadata metadata, Exception exception) {

		if (exception != null) {
			LOGGER.error(exception.toString());
		} else {
			LOGGER.info(" ** Return Metadata ** \n" + 
						"	Topic --> " + metadata.topic() + "\n" + 
						"	Offset --> " + metadata.offset() + "\n" + 
						"	Partition --> " + metadata.partition() + "\n" + 
						"	Timestamp --> " + metadata.timestamp());
		}

	}
}
