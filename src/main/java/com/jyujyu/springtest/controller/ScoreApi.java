package com.jyujyu.springtest.controller;

import java.util.List;

import org.hibernate.type.descriptor.java.ObjectArrayJavaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jyujyu.springtest.dto.request.SaveExamScoreRequest;
import com.jyujyu.springtest.dto.response.ExamFailStudentResponse;
import com.jyujyu.springtest.dto.response.ExamPassStudentResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
public class ScoreApi {

	@PutMapping("/exam/{exam}/score")
	public Object saveScore(
		@PathVariable String exam,
		@RequestBody SaveExamScoreRequest request
	) {
		return request;
	}

	@GetMapping("/exam/{exam}/pass")
	public List<ExamPassStudentResponse> passExam(
		@PathVariable String exam
	) {
		return List.of(
			ExamPassStudentResponse.of("jyujyu", 60.0)
		);
	}

	@GetMapping("/exam/{exam}/fail")
	public List<ExamFailStudentResponse> failExam(
		@PathVariable String exam
	) {
		return List.of(
			ExamFailStudentResponse.of("jyujyu", 59.9)
		);
	}


}
