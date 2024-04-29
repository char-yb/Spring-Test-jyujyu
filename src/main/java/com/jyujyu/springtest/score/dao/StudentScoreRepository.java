package com.jyujyu.springtest.score.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyujyu.springtest.score.domain.StudentScore;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
}
