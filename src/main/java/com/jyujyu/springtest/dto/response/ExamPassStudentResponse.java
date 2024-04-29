package com.jyujyu.springtest.dto.response;

public record ExamPassStudentResponse(
	String studentName,
	Double avgScore
) {
	public static ExamPassStudentResponse of(String studentName, Double avgScore) {
		return new ExamPassStudentResponse(studentName, avgScore);
	}
}
