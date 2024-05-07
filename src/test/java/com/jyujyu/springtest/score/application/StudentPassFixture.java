package com.jyujyu.springtest.score.application;

import com.jyujyu.springtest.MyCalculator;
import com.jyujyu.springtest.score.domain.StudentPass;
import com.jyujyu.springtest.score.domain.StudentScore;

public class StudentPassFixture {

	public static StudentPass create(StudentScore studentScore) {
		var calculator = new MyCalculator();
		return StudentPass.builder()
			.exam(studentScore.getExam())
			.studentName(studentScore.getStudentName())
			.avgScore(calculator
				.add(studentScore.getKorScore().doubleValue())
				.add(studentScore.getEngScore().doubleValue())
				.add(studentScore.getMathScore().doubleValue())
				.div(3.0)
				.getResult()
			)
			.build();
	}

	public static StudentPass create(String studentName, String exam) {
		return StudentPass
			.builder()
			.studentName(studentName)
			.exam(exam)
			.avgScore(80.0)
			.build();
	}
}
