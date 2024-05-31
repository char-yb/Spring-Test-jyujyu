package com.jyujyu.springtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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

	@Test
	@DisplayName("request 패키지 안에 있는 클래스들은 Request로 끝나야 한다.")
	public void requestTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// request 패키지 안에 있는 클래스들은 Request로 끝나야 한다.
			// 뒤에 ..을 붙이면 하위 패키지까지 검증
			.that().resideInAPackage("..request..")
			// Request 패키지 안에 있는 클래스들은 이렇게 request 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Request");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("response 패키지 안에 있는 클래스들은 Response로 끝나야 한다.")
	public void responseTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// response 패키지 안에 있는 클래스들은 Response로 끝나야 한다.
			// 뒤에 ..을 붙이면 하위 패키지까지 검증
			.that().resideInAPackage("..response..")
			// Response 패키지 안에 있는 클래스들은 이렇게 response 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Response");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("repository 패키지 안에 있는 클래스들은 Repository로 끝나야 하고, 인터페이스이여야 한다.")
	public void repositoryTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// repository 패키지 안에 있는 클래스들은 Repository로 끝나야 한다.
			// 뒤에 ..을 붙이면 하위 패키지까지 검증
			.that().resideInAnyPackage("..dao..")
			// Repository 패키지 안에 있는 클래스들은 이렇게 repository 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Repository")
			.andShould().beInterfaces();

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("service 패키지 안에 있는 클래스들은 Service로 끝나야 하고, @Service 어노테이션을 가져야 한다.")
	public void serviceAnnotationTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// service 패키지 안에 있는 클래스들은 Service로 끝나야 한다.
			// 뒤에 ..을 붙이면 하위 패키지까지 검증
			.that().resideInAnyPackage("..service..")
			// Service 패키지 안에 있는 클래스들은 이렇게 service 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Service")
			.andShould().beAnnotatedWith(Service.class);

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("config 패키지 안에 있는 클래스들은 Config로 끝나야 하고, @Configuration 어노테이션을 가져야 한다.")
	public void configAnnotationTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			// config 패키지 안에 있는 클래스들은 Config로 끝나야 한다.
			// 뒤에 ..을 붙이면 하위 패키지까지 검증
			.that().resideInAnyPackage("..config..")
			// Config 패키지 안에 있는 클래스들은 이렇게 config 라는 이름으로 끝나야 합니다. 라는 룰 정의
			.should().haveSimpleNameEndingWith("Config")
			.andShould().beAnnotatedWith(Configuration.class);

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Controller는 Request/Response를 사용할 수 없다.")
	public void controllerDependencyModelTest() {
		// ArchRule rule 생성
		ArchRule rule = classes()
			.that().resideInAPackage("..api")
			.should().dependOnClassesThat()
			// Controller는 request, response, service 패키지에 의존한다는 의미
			.resideInAnyPackage("..request..", "..response..", "..service..");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("어떠한 클래스도 컨트롤러를 의존할 수 없다.")
	public void controllerDependencyTest() {
		ArchRule rule = classes()
			// api 패키지 안에 있는 클래스들은
			.that().resideInAnyPackage("..api")
			// api 패키지 안에 있는 클래스로만 의존이 되어야 한다.
			.should().onlyHaveDependentClassesThat().resideInAnyPackage("..api");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Controller는 Model을 사용할 수 없다.")
	public void controllerModelTest() {
		// ArchRule rule 생성
		// noClasses는 부정형이 된다. 즉, 의존한다는 의미를 부정하는 것을 검증한다.
		ArchRule rule = noClasses()
			.that().resideInAnyPackage("..api")
			.should().dependOnClassesThat()
			// Controller는 model 패키지에 의존한다는 의미
			.resideInAnyPackage("..domain..");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Service는 Controller를 의존할 수 없다.")
	public void serviceDependencyTest() {
		ArchRule rule = noClasses()
			// service 패키지 안에 있는 클래스들은
			.that().resideInAnyPackage("..service..")
			// service 패키지 안에 있는 클래스로만 의존이 되어야 한다.
			.should().dependOnClassesThat().resideInAnyPackage("..api");

		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Model은 오직 Service와 Repository에 의해 의존됨.")
	public void modelDependencyTest() {
		ArchRule rule = classes()
			// domain 패키지 안에 있는 클래스들은
			.that().resideInAnyPackage("..domain..")
			.should().onlyHaveDependentClassesThat().resideInAnyPackage(
				"..dao..", "..application..", "..domain..", "..dto.."
			);

		/*
		 * Builder 클래스 같은 경우는 Lombok이 자동으로 만들어주는 클래스여서 ArchUnit을 통해 어떤 의존성을 가지고 있는 지 체크
		 */
		rule.check(javaClasses);
	}

	@Test
	@DisplayName("Model은 아무것도 의존하지 않는다.")
	public void modelDependencyModelTest() {
		ArchRule rule = classes()
			.that().resideInAnyPackage("..domain..")
			.should().onlyDependOnClassesThat()
			// self-참조
			.resideInAnyPackage("..domain..", "java..", "jakarta..");

		rule.check(javaClasses);
	}

}
