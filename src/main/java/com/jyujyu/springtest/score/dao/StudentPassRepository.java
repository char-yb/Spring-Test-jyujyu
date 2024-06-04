package com.jyujyu.springtest.score.dao;

import com.jyujyu.springtest.score.domain.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {}
