package com.jyujyu.springtest;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MyCalculatorRepeatableTest {

	@DisplayName("덧셈을 5번 반복하며 테스트")
	@RepeatedTest(5)
	public void repeatAddTest() {
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.add(10.0);
		Assertions.assertEquals(10.0, myCalculator.getResult());
	}
	
	/*
	이렇게 반복적으로 실행될 경우 getResult 메서드의
	구현을 바꾼 것과 같은 케이스에 대해 대응이 어렵다.
	 */
	@DisplayName("덧셈을 5번 파라미터 2개를 넣어주며 반복 테스트")
	@ParameterizedTest
	// method 내에 인자 값을 줄 수 있다.
	@MethodSource("parameterizedTestParameters")
	public void parameterizedAddTest(Double value, Double expected) {
		MyCalculator myCalculator = new MyCalculator(0.0);
		myCalculator.add(value);
		Assertions.assertEquals(expected, myCalculator.getResult());
	}

	public static Stream<Arguments> parameterizedTestParameters() {
		return Stream.of(
			Arguments.of(10.0, 10.0),	// 첫 번째는 더할 값, 두 번째는 기댓값이다.
			Arguments.of(20.0, 20.0),
			Arguments.of(30.0, 30.0)
		);
	}

	@DisplayName("복잡한 계산을 3번 파라미터 4개를 넣어주며 반복 테스트")
	@ParameterizedTest
	@MethodSource("parameterizedComplicatedCalculateTestParameters")
	public void parameterizedComplicatedCalculateTest(
		Double addValue,
		Double minusValue,
		Double mulValue,
		Double divValue,
		Double expected
	) {
		// given
		MyCalculator myCalculator = new MyCalculator(0.0);

		// when
		myCalculator
			.add(addValue)
			.sub(minusValue)
			.mul(mulValue)
			.div(divValue);
		myCalculator.div(divValue);

		// then
		Assertions.assertEquals(expected, myCalculator.getResult());
	}

	public static Stream<Arguments> parameterizedComplicatedCalculateTestParameters() {
		return Stream.of(
			Arguments.of(10.0, 5.0, 2.0, 2.0, 2.5),
			Arguments.of(20.0, 10.0, 2.0, 2.0, 5.0),
			Arguments.of(30.0, 15.0, 2.0, 2.0, 7.5)
		);
	}
}
