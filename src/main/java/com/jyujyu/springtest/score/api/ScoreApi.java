package com.jyujyu.springtest.score.api;

import com.jyujyu.springtest.score.application.StudentScoreService;
import com.jyujyu.springtest.score.dto.request.SaveExamScoreRequest;
import com.jyujyu.springtest.score.dto.response.ExamFailStudentResponse;
import com.jyujyu.springtest.score.dto.response.ExamPassStudentResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreApi {

  private final StudentScoreService studentScoreService;

  @PutMapping("/exam/{exam}/score")
  public void saveScore(@PathVariable String exam, @RequestBody SaveExamScoreRequest request) {
    studentScoreService.saveScore(
        request.studentName(), exam, request.korScore(), request.engScore(), request.mathScore());
  }

  @GetMapping("/exam/{exam}/pass")
  public List<ExamPassStudentResponse> passExam(@PathVariable String exam) {
    return studentScoreService.getPassStudents(exam);
  }

  @GetMapping("/exam/{exam}/fail")
  public List<ExamFailStudentResponse> failExam(@PathVariable String exam) {
    return studentScoreService.getFailStudents(exam);
  }
}
