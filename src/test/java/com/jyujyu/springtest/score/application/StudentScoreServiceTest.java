package com.jyujyu.springtest.score.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.jyujyu.springtest.score.dao.StudentFailRepository;
import com.jyujyu.springtest.score.dao.StudentPassRepository;
import com.jyujyu.springtest.score.dao.StudentScoreRepository;
import com.jyujyu.springtest.score.domain.StudentFail;
import com.jyujyu.springtest.score.domain.StudentPass;
import com.jyujyu.springtest.score.domain.StudentScore;
import com.jyujyu.springtest.score.dto.response.ExamFailStudentResponse;
import com.jyujyu.springtest.score.dto.response.ExamPassStudentResponse;
import com.jyujyu.springtest.score.model.StudentScoreTestDataBuilder;

class StudentScoreServiceTest {

	private StudentScoreService studentScoreService;
	private StudentScoreRepository studentScoreRepository;
	private StudentPassRepository studentPassRepository;
	private StudentFailRepository studentFailRepository;

	@BeforeEach
	public void setUp() {
		studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
		studentPassRepository = Mockito.mock(StudentPassRepository.class);
		studentFailRepository = Mockito.mock(StudentFailRepository.class);
		studentScoreService = new StudentScoreService(
			studentScoreRepository,
			studentPassRepository,
			studentFailRepository
		);
	}

	@Test
	@DisplayName("첫 번째 성적 저장 테스트")
	public void firstSaveScoreMockTest() {
		// given
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
		StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed().build();
		StudentPass expectStudentPass = StudentPassFixture.create(expectStudentScore);

		ArgumentCaptor<StudentScore> studentScoreCaptor = ArgumentCaptor.forClass(StudentScore.class);
		ArgumentCaptor<StudentPass> studentPassCaptor = ArgumentCaptor.forClass(StudentPass.class);

		// when
		studentScoreService.saveScore(
			expectStudentScore.getStudentName(),
			expectStudentScore.getExam(),
			expectStudentScore.getKorScore(),
			expectStudentScore.getEngScore(),
			expectStudentScore.getMathScore()
		);

		// then
		// Mockito.any()는 어떤 객체라도 매치하여 실행되도록 함
		// Mockito.times(1)은 해당 메서드가 1번 호출되었는지 검증
		// studentScoreCaptor.capture()로 저장된 객체를 가져올 수 있음
		Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreCaptor.capture());

		// StudentScore 객체의 값이 예상대로 저장되었는지 확인
		// ArgumentCaptor.capture()로 저장된 객체를 가져올 수 있음
		// saveScore 라는 메서드의 로직을 정확히 작성했는지 인자값을 캡처함으로써 확인 가능
		StudentScore capturedStudentScore = studentScoreCaptor.getValue();
		Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
		Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
		Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
		Assertions.assertEquals(expectStudentScore.getEngScore(), capturedStudentScore.getEngScore());
		Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());

		Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassCaptor.capture());

		StudentPass capturedStudentPass = studentPassCaptor.getValue();
		Assertions.assertEquals(expectStudentPass.getStudentName(), capturedStudentPass.getStudentName());
		Assertions.assertEquals(expectStudentPass.getExam(), capturedStudentPass.getExam());
		Assertions.assertEquals(expectStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());

		// fail 저장은 호출되지 않았으므로 never()를 사용 (times(0)과 동일)
		Mockito.verify(studentFailRepository, Mockito.never()).save(Mockito.any());
	}

	@Test
	@DisplayName("성적 저장 로직 검증 / 평균 점수가 60점 미만인 경우")
	public void saveScoreLogicTest2() {
		// given: 평균 점수가 60점 미만인 경우
		// StudentScore expectStudentScore = StudentScoreTestDataBuilder.failed().build();
		// Fixture 클래스를 통해 Builder의 자유도를 방지한다.
		StudentScore expectStudentScore = StudentScoreFixture.failed();
		StudentFail expectStudentFail = StudentFailFixture.create(expectStudentScore);

		ArgumentCaptor<StudentScore> studentScoreCaptor = ArgumentCaptor.forClass(StudentScore.class);
		ArgumentCaptor<StudentFail> studentFailCaptor = ArgumentCaptor.forClass(StudentFail.class);

		// when
		studentScoreService.saveScore(
			expectStudentScore.getStudentName(),
			expectStudentScore.getExam(),
			expectStudentScore.getKorScore(),
			expectStudentScore.getEngScore(),
			expectStudentScore.getMathScore()
		);

		// then
		// Mockito.any()는 어떤 객체라도 매치하여 실행되도록 함
		// Mockito.times(1)은 해당 메서드가 1번 호출되었는지 검증
		Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreCaptor.capture());

		StudentScore capturedStudentScore = studentScoreCaptor.getValue();
		Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
		Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
		Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
		Assertions.assertEquals(expectStudentScore.getEngScore(), capturedStudentScore.getEngScore());
		Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());

		// pass 저장은 호출되지 않았으므로 never()를 사용 (times(0)과 동일)
		Mockito.verify(studentPassRepository, Mockito.never()).save(Mockito.any());
		Mockito.verify(studentFailRepository, Mockito.times(1)).save(studentFailCaptor.capture());

		StudentFail capturedStudentFail = studentFailCaptor.getValue();
		Assertions.assertEquals(expectStudentFail.getStudentName(), capturedStudentFail.getStudentName());
		Assertions.assertEquals(expectStudentFail.getExam(), capturedStudentFail.getExam());
		Assertions.assertEquals(expectStudentFail.getAvgScore(), capturedStudentFail.getAvgScore());
	}

	@Test
	@DisplayName("합격자 목록 조회 테스트")
	public void getPassStudentsTest() {
		// given
		String givenExam = "testExam";
		StudentPass expectedStudent1 = StudentPassFixture.create("bsj1", givenExam);
		StudentPass expectedStudent2 = StudentPassFixture.create("bsj2", givenExam);
		StudentPass notExpectedStudent = StudentPassFixture.create("bsj3", "secondExam");

		when(studentPassRepository.findAll())
			.thenReturn(List.of(expectedStudent1, expectedStudent2, notExpectedStudent));

		// when
		List<ExamPassStudentResponse> responses = studentScoreService.getPassStudents("testExam");
		var expectedResponses =
			Stream.of(expectedStudent1, expectedStudent2)
				.map(pass -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
				.toList();

		assertThatIterable(responses).isEqualTo(expectedResponses);
	}

	@Test
	@DisplayName("불합격자 명단 가져오기 검증")
	void getFailStudentList() {
		// given
		String givenExam = "testExam";
		StudentFail expectedStudent1 = StudentFailFixture.create("bsj1", givenExam);
		StudentFail expectedStudent2 = StudentFailFixture.create("bsj2", givenExam);
		StudentFail notExpectedStudent = StudentFailFixture.create("bsj3", "secondExam");

		when(studentFailRepository.findAll())
			.thenReturn(List.of(expectedStudent1, expectedStudent2, notExpectedStudent));

		// when
		List<ExamFailStudentResponse> responses = studentScoreService.getFailStudents("testExam");
		var expectedResponses =
			Stream.of(expectedStudent1, expectedStudent2)
				.map(pass -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore()))
				.toList();

		// then
		assertThatIterable(responses).isEqualTo(expectedResponses);
	}
}