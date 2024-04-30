package com.jyujyu.springtest.score.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.jyujyu.springtest.score.dao.StudentFailRepository;
import com.jyujyu.springtest.score.dao.StudentPassRepository;
import com.jyujyu.springtest.score.dao.StudentScoreRepository;

class StudentScoreServiceTest {

	@Test
	@DisplayName("첫 번째 성적 저장 테스트")
	public void firstSaveScoreMockTest() {
		// given
		StudentScoreService studentScoreService = new StudentScoreService(
			// Mock 객체를 StudentScoreService 생성자에 주입
			Mockito.mock(StudentScoreRepository.class),
			Mockito.mock(StudentPassRepository.class),
			Mockito.mock(StudentFailRepository.class)
		);
		String studentName = "jyujyu";
		String exam = "midterm";
		Integer korScore = 80;
		Integer engScore = 100;
		Integer mathScore = 60;

		// when
		studentScoreService.saveScore(
			studentName,
			exam,
			korScore,
			engScore,
			mathScore
		);

		// then

	}
}