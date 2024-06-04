package com.jyujyu.springtest.score.dao;

import com.jyujyu.springtest.score.domain.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {}
