package com.jyujyu.springtest;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jyujyu.springtest.score.application.StudentScoreFixture;
import com.jyujyu.springtest.score.dao.StudentScoreRepository;
import com.jyujyu.springtest.score.domain.StudentScore;

import jakarta.persistence.EntityManager;

class SpringtestApplicationTests extends IntegrationTest {

	@Autowired
	private StudentScoreRepository studentScoreRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {

		// 실제로 DB에 insert를 할 수 있는 지 확인하는 테스트
		var studentScore = StudentScoreFixture.passed();
		StudentScore savedStudentScore = studentScoreRepository.save(studentScore);

		// query가 강제로 날아갈 수 있도록 flush, clear를 해준다.
		entityManager.flush();
		entityManager.clear();

		StudentScore queryStudentScore =
			studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();

		assertThat(savedStudentScore.getId()).isEqualTo(queryStudentScore.getId());
		assertThat(savedStudentScore.getEngScore()).isEqualTo(queryStudentScore.getEngScore());
		assertThat(savedStudentScore.getKorScore()).isEqualTo(queryStudentScore.getKorScore());
		assertThat(savedStudentScore.getStudentName()).isEqualTo(queryStudentScore.getStudentName());
		Assertions.assertEquals(savedStudentScore.getId(), queryStudentScore.getId());

		/*
		Hibernate가 query를 실행했다는 로그도 확인이 될 것이다.
		즉 테스트가 동작하는 중간에 이제 Docker Compose 컨테이너를 통해 MySQL 컨테이너를 띄우고
		그 MySQL 컨테이너를 Flyway로 테이블을 생성해주고 StudentScoreFixture 객체를 생성해 insert를 해주는 테스트
		 */
	}

}
