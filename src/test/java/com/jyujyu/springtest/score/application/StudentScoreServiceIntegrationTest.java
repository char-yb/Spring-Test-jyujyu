package com.jyujyu.springtest.score.application;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jyujyu.springtest.IntegrationTest;
import com.jyujyu.springtest.MyCalculator;
import com.jyujyu.springtest.score.domain.StudentScore;
import com.jyujyu.springtest.score.dto.response.ExamFailStudentResponse;
import com.jyujyu.springtest.score.dto.response.ExamPassStudentResponse;

import jakarta.persistence.EntityManager;

public class StudentScoreServiceIntegrationTest extends IntegrationTest {

	@Autowired
	private StudentScoreService studentScoreService;

	@Autowired
	private EntityManager entityManager;

	@Test
	public void savePassedStudentScoreTest() {

		// given
		StudentScore passed = StudentScoreFixture.passed();

		// when
		studentScoreService.saveScore(
			passed.getStudentName(),
			passed.getExam(),
			passed.getKorScore(),
			passed.getEngScore(),
			passed.getMathScore()
		);

		entityManager.flush();
		entityManager.clear();

		// then
		List<ExamPassStudentResponse> passStudents = studentScoreService.getPassStudents(passed.getExam());

		Assertions.assertEquals(1, passStudents.size());

		MyCalculator calculator = new MyCalculator(0.0);

		ExamPassStudentResponse passStudent = passStudents.get(0);
		Assertions.assertEquals(passed.getStudentName(), passStudent.studentName());
		Assertions.assertEquals(
			calculator
				.add(passed.getKorScore().doubleValue())
				.add(passed.getEngScore().doubleValue())
				.add(passed.getMathScore().doubleValue())
				.div(3.0)
				.getResult(), passStudent.avgScore());
	}

	@Test
	public void saveFailedStudentScoreTest() {
		// given
		StudentScore failed = StudentScoreFixture.failed();

		// when
		studentScoreService.saveScore(
			failed.getStudentName(),
			failed.getExam(),
			failed.getKorScore(),
			failed.getEngScore(),
			failed.getMathScore()
		);
		entityManager.flush();
		entityManager.clear();

		// then
		List<ExamFailStudentResponse> failedStudents = studentScoreService.getFailStudents(failed.getExam());
		Assertions.assertEquals(1, failedStudents.size());

		MyCalculator calculator = new MyCalculator(0.0);
		ExamFailStudentResponse examFailStudentResponse = failedStudents.get(0);

		Assertions.assertEquals(failed.getStudentName(), examFailStudentResponse.studentName());
		Assertions.assertEquals(
			calculator
				.add(failed.getKorScore().doubleValue())
				.add(failed.getEngScore().doubleValue())
				.add(failed.getMathScore().doubleValue())
				.div(3.0)
				.getResult(), examFailStudentResponse.avgScore());

	}
}
