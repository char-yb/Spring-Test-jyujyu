package com.jyujyu.springtest.dto.request;

public record SaveExamScoreRequest (
	String studentName,
	Integer korScore,
	Integer engScore,
 	Integer mathScore
) {

}
