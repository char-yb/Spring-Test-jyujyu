package com.jyujyu.springtest.score.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyujyu.springtest.MyCalculator;
import com.jyujyu.springtest.score.dao.StudentFailRepository;
import com.jyujyu.springtest.score.dao.StudentPassRepository;
import com.jyujyu.springtest.score.dao.StudentScoreRepository;
import com.jyujyu.springtest.score.domain.StudentFail;
import com.jyujyu.springtest.score.domain.StudentPass;
import com.jyujyu.springtest.score.domain.StudentScore;
import com.jyujyu.springtest.score.dto.response.ExamFailStudentResponse;
import com.jyujyu.springtest.score.dto.response.ExamPassStudentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentScoreService {

	private final StudentScoreRepository studentScoreRepository;
	private final StudentPassRepository studentPassRepository;
	private final StudentFailRepository studentFailRepository;
	public void saveScore(String studentName, String exam, Integer korScore, Integer engScore, Integer mathScore) {
		StudentScore studentScore = StudentScore.builder()
				.studentName(studentName)
				.exam(exam)
				.korScore(korScore)
				.engScore(engScore)
				.mathScore(mathScore)
				.build();
		studentScoreRepository.save(studentScore);

		MyCalculator calculator = new MyCalculator(0.0);
		Double avgScore = calculator
			.add(korScore.doubleValue())
			.add(engScore.doubleValue())
			.add(mathScore.doubleValue())
			.div(3.0)
			.getResult();

		if (avgScore >= 60.0) {
			studentPassRepository.save(
				StudentPass.builder()
					.studentName(studentName)
					.exam(exam)
					.avgScore(avgScore)
					.build()
			);
		} else {
			studentFailRepository.save(
				StudentFail.builder()
					.studentName(studentName)
					.exam(exam)
					.avgScore(avgScore)
					.build()
			);
		}
	}

	public List<ExamPassStudentResponse> getPassStudents(String exam) {
		List<StudentPass> studentPasses = studentPassRepository.findAll();

		return studentPasses.stream()
			.filter(studentPass -> studentPass.getExam().equals(exam))
			.map(ExamPassStudentResponse::from)
			.toList();
	}

	public List<ExamFailStudentResponse> getFailStudents(String exam) {
		List<StudentFail> studentFails = studentFailRepository.findAll();

		return studentFails.stream()
			.filter(studentFail -> studentFail.getExam().equals(exam))
			.map(ExamFailStudentResponse::from)
			.toList();
	}

}
