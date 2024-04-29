package com.jyujyu.springtest.score.dto.response;

import com.jyujyu.springtest.score.domain.StudentFail;

public record ExamFailStudentResponse(
	String studentName,
	Double avgScore
) {
	public static ExamFailStudentResponse of(String studentName, Double avgScore) {
		return new ExamFailStudentResponse(studentName, avgScore);
	}

	public static ExamFailStudentResponse from(StudentFail studentFail) {
		return new ExamFailStudentResponse(studentFail.getStudentName(), studentFail.getAvgScore());
	}
}
