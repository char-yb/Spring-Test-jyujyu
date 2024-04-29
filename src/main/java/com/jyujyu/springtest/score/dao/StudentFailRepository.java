package com.jyujyu.springtest.score.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyujyu.springtest.score.domain.StudentFail;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {
}
