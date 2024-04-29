package com.jyujyu.springtest.dto.response;

public record ExamFailStudentResponse(
	String studentName,
	Double avgScore
) {
	public static ExamFailStudentResponse of(String studentName, Double avgScore) {
		return new ExamFailStudentResponse(studentName, avgScore);
	}
}
