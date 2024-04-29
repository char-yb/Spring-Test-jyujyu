package com.jyujyu.springtest.score.dto.response;

import com.jyujyu.springtest.score.domain.StudentPass;

public record ExamPassStudentResponse(
	String studentName,
	Double avgScore
) {
	public static ExamPassStudentResponse of(String studentName, Double avgScore) {
		return new ExamPassStudentResponse(studentName, avgScore);
	}

	public static ExamPassStudentResponse from(StudentPass studentPass) {
		return new ExamPassStudentResponse(studentPass.getStudentName(), studentPass.getAvgScore());
	}
}
