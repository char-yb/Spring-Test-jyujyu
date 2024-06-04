package com.jyujyu.springtest.score.application;

import com.jyujyu.springtest.score.domain.StudentScore;

public class StudentScoreFixture {

  public static StudentScore passed() {
    return StudentScore.builder()
        .studentName("jyujyu")
        .korScore(80)
        .engScore(100)
        .mathScore(60)
        .exam("defaultExam")
        .build();
  }

  public static StudentScore failed() {
    return StudentScore.builder()
        .studentName("jyujyu")
        .korScore(50)
        .engScore(40)
        .mathScore(30)
        .exam("midterm")
        .build();
  }
}
