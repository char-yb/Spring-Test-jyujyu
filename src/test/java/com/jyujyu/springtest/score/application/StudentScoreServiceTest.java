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

	@Test
	@DisplayName("성적 저장 로직 검증 / 평균 점수가 60점 이상인 경우")
	public void saveScoreLogicTest() {
		// given: 평균 점수가 80점 이상인 경우
		StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
		StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
		StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
		StudentScoreService studentScoreService = new StudentScoreService(
			// Use the same mock objects here
			studentScoreRepository,
			studentPassRepository,
			studentFailRepository
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
		// Mockito.any()는 어떤 객체라도 매치하여 실행되도록 함
		// Mockito.times(1)은 해당 메서드가 1번 호출되었는지 검증
		Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
		Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
		// fail 저장은 호출되지 않았으므로 never()를 사용 (times(0)과 동일)
		Mockito.verify(studentFailRepository, Mockito.never()).save(Mockito.any());
	}

	@Test
	@DisplayName("성적 저장 로직 검증 / 평균 점수가 60점 미만인 경우")
	public void saveScoreLogicTest2() {
		// given: 평균 점수가 60점 미만인 경우
		StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
		StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
		StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);
		StudentScoreService studentScoreService = new StudentScoreService(
			// Use the same mock objects here
			studentScoreRepository,
			studentPassRepository,
			studentFailRepository
		);

		String studentName = "jyujyu";
		String exam = "midterm";
		Integer korScore = 50;
		Integer engScore = 40;
		Integer mathScore = 30;

		// when
		studentScoreService.saveScore(
			studentName,
			exam,
			korScore,
			engScore,
			mathScore
		);

		// then
		// Mockito.any()는 어떤 객체라도 매치하여 실행되도록 함
		// Mockito.times(1)은 해당 메서드가 1번 호출되었는지 검증
		Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
		// pass 저장은 호출되지 않았으므로 never()를 사용 (times(0)과 동일)
		Mockito.verify(studentPassRepository, Mockito.never()).save(Mockito.any());
		Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());
	}
}