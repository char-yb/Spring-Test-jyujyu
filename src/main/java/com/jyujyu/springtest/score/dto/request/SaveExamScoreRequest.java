package com.jyujyu.springtest.score.dto.request;

public record SaveExamScoreRequest (
	String studentName,
	Integer korScore,
	Integer engScore,
 	Integer mathScore
) {

}
