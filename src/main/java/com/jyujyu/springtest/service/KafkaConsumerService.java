package com.jyujyu.springtest.service;

import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  public void process(String message) {
    System.out.println("Processing message: " + message);
  }
}
