package com.jyujyu.springtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jyujyu.springtest.service.KafkaConsumerService;
import com.jyujyu.springtest.service.KafkaProducerService;

@Order(0)
public class KafkaConsumerApplicationTest extends IntegrationTest {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	/* MockBean
	 * 	Spring Context를 로드했을 때 어떤 빈을 Mocking하고 싶을 때, @MockBean을 사용한다.
	 */
	@MockBean
	private KafkaConsumerService kafkaConsumerService;

	@Test
	public void kafkaSendAndConsumeTest() {
		// given
		String topic = "test-topic";
		String expectedValue = "expect-value";

		// when
		kafkaProducerService.sendMessage(topic, expectedValue);

		// then
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		// 실제로 kafkaConsumerService에서 process 메소드가 호출되었는지 확인
		Mockito.verify(kafkaConsumerService, Mockito.timeout(5000).times(1))
			.process(stringArgumentCaptor.capture());
		Assertions.assertEquals(expectedValue, stringArgumentCaptor.getValue());
		/**
		 * kafkaProducerService에서 test-topic으로 expect-value라는 것을 넣어 호출을 했을 때,
		 * Template를 통해 Producer가 호출되고, Producer가 Kafka에 메시지를 보낸다.
		 * 만약 kafkaConsumerService에서 listening 중인 Topic이 test-topic이라면,
		 * 해당 메시지를 받아서 process 메소드를 호출하게 된다.
		 */
	}

}