package com.jyujyu.springtest;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import com.jyujyu.springtest.service.KafkaConsumerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerApplication {
	private final KafkaConsumerService kafkaConsumerService;

	@Bean
	public NewTopic topic() {
		// kafka의 topic은 redis의 key와 비슷한 성질을ㄹ 가지고 있다.
		return TopicBuilder.name("test-topic").build();
	}

	@KafkaListener(id = "test-id", topics = "test-topic")
	public void listen(String message) {
		kafkaConsumerService.process(message);
	}
}
