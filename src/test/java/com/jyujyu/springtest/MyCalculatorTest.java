package com.jyujyu.springtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyCalculatorTest {

  @Test
  @DisplayName("MyCalculator 덧셈 테스트")
  void addTest() {
    // 아래 세 가지의 행위를 AAA 패턴이라 칭한다.
    // Arrange - 준비
    MyCalculator myCalculator = new MyCalculator();

    // Act - 행동
    myCalculator.add(10.0);

    // Assert - 단면/검증
    Assertions.assertEquals(10.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 뺄셈 테스트")
  void subTest() {
    // GWT 패턴, AAA와 유사
    // given
    MyCalculator myCalculator = new MyCalculator(10.0);

    // when
    myCalculator.sub(5.0);

    // then
    Assertions.assertEquals(5.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 곱셈 테스트")
  void mul() {
    MyCalculator myCalculator = new MyCalculator(2.0);

    myCalculator.mul(3.0);
    Assertions.assertEquals(6.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 나눗셈 테스트")
  void div() {
    MyCalculator myCalculator = new MyCalculator(10.0);

    myCalculator.div(2.0);
    Assertions.assertEquals(5.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 사칙연산 테스트")
  void complicatedCalculateTest() {
    // given
    MyCalculator myCalculator = new MyCalculator(0.0);

    // when
    myCalculator.add(10.0).sub(2.0).mul(2.0).div(4.0);
    myCalculator.div(2.0);

    // then
    Assertions.assertEquals(2.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 0으로 나누었을때 ZeroDivisionException throws 테스트")
  void divideZeroTest() {
    // given
    MyCalculator myCalculator = new MyCalculator(10.0);

    // when & then
    Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () -> myCalculator.div(0.0));
  }
}
