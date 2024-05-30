package com.jyujyu.springtest.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	// Send message to Kafka
	public void sendMessage(String topic, String message) {
		kafkaTemplate.send(topic, message);
	}
}
