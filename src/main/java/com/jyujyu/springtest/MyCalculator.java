package com.jyujyu.springtest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyCalculator {

	private Double result = 0.0;

	public MyCalculator add(Double num) {
		this.result += num;
		return this;
	}

	public MyCalculator sub(Double num) {
		this.result -= num;
		return this;
	}

	public MyCalculator mul(Double num) {
		this.result *= num;
		return this;
	}

	public MyCalculator div(Double num) {
		if (num == 0.0) {
			throw new ZeroDivisionException();
		}

		this.result /= num;
		return this;
	}

	public static class ZeroDivisionException extends RuntimeException {

	}
}
