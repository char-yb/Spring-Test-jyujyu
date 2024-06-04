package com.jyujyu.springtest.score.model;

import com.jyujyu.springtest.score.domain.StudentScore;

// 데이터를 생성하는 클래스로 Test에 대한 데이터를 Builder 클래스로 리턴받도록 한다.
// Builder 패턴의 특징으로 오버라이딩을 활용해 커스텀하여 사용할 수도 있다.
public class StudentScoreTestDataBuilder {

  public static StudentScore.StudentScoreBuilder passed() {
    return StudentScore.builder()
        .studentName("jyujyu")
        .korScore(80)
        .engScore(100)
        .mathScore(60)
        .exam("defaultExam");
  }

  public static StudentScore.StudentScoreBuilder failed() {
    return StudentScore.builder()
        .studentName("jyujyu")
        .korScore(50)
        .engScore(40)
        .mathScore(30)
        .exam("midterm");
  }
}
