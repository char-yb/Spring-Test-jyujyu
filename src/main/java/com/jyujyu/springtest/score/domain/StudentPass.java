package com.jyujyu.springtest.score.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(name = "student_pass")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class StudentPass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_pass_id")
	private Long id;

	@Column(name = "exam")
	private String exam;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "avg_score")
	private Double avgScore;
}
