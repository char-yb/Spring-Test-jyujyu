package com.jyujyu.springtest.score.application;

import com.jyujyu.springtest.MyCalculator;
import com.jyujyu.springtest.score.domain.StudentFail;
import com.jyujyu.springtest.score.domain.StudentScore;

public class StudentFailFixture {
	public static StudentFail create(StudentScore studentScore) {
		return StudentFail.builder()
			.studentName(studentScore.getStudentName())
			.exam(studentScore.getExam())
			.avgScore(
				(new MyCalculator(0.0))
					.add(studentScore.getKorScore().doubleValue())
					.add(studentScore.getEngScore().doubleValue())
					.add(studentScore.getMathScore().doubleValue())
					.div(3.0)
					.getResult()
			)
			.build();
	}

	public static StudentFail create(String studentName, String exam) {
		return StudentFail.builder()
			.studentName(studentName)
			.exam(exam)
			.avgScore(40.0)
			.build();
	}
}
