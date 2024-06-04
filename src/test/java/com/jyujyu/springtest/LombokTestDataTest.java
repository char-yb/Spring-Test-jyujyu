package com.jyujyu.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {

  @Test
  public void testDataTest() {
    TestData testData = new TestData();
    testData.setName("Hello jyujyu!");
    Assertions.assertEquals("Hello jyujyu!", testData.getName());
  }
}
