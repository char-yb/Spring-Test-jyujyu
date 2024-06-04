package com.jyujyu.springtest.score.dao;

import com.jyujyu.springtest.score.domain.StudentFail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {}
