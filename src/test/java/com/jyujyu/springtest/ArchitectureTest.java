package com.jyujyu.springtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;

public class ArchitectureTest {
	JavaClasses javaClasses;

	@BeforeEach
	public void beforeEach() {
		// javaClasses 초기화
		javaClasses = new ClassFileImporter()
			.withImportOption(
				// 테스트 클래스들은 이 검증에서 이제 빠진다라는 의미를 가지고 있다.
				new ImportOption.DoNotIncludeTests())
					.importPackages("com.jyujyu.springtest");
	}

	@Test
	@DisplayName("Controller 패키지 안에 있는 클래스들은 Controller로 끝나야 한다.")
	public void controllerTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// controller 패키지 안에 있는 클래스들은 Controller로 끝나야 한다.
			.that().resideInAPackage("..api")
			// Controller 패키지 안에 있는 클래스들은 이렇게 api 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Api");

		// Controller 또는 RestController 어노테이션을 가지고 있는 클래스 검증
		ArchRule controllerAnnotationRule = classes()
			.that().resideInAPackage("..api")
			.should().beAnnotatedWith(Controller.class)
			.orShould().beAnnotatedWith(RestController.class);
			// .orShould().beAnnotatedWith("org.springframework.web.bind.annotation.RestController");

		rule.check(javaClasses);
		controllerAnnotationRule.check(javaClasses);
	}

	@Test
	@DisplayName("Application 패키지 안에 있는 클래스들은 Service로 끝나야 한다.")
	public void applicationPackageTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// service 패키지 안에 있는 클래스들은 Service로 끝나야 한다.
			.that().resideInAPackage("..application")
			// Service 패키지 안에 있는 클래스들은 이렇게 service 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Service");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Service 패키지 안에 있는 클래스들은 Service로 끝나야 한다.")
	public void serviceTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// service 패키지 안에 있는 클래스들은 Service로 끝나야 한다.
			.that().resideInAPackage("..service")
			// Service 패키지 안에 있는 클래스들은 이렇게 service 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Service");

		rule.check(javaClasses);
	}


}
