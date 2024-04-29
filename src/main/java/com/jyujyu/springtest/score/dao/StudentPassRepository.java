package com.jyujyu.springtest.score.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyujyu.springtest.score.domain.StudentPass;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {
}
