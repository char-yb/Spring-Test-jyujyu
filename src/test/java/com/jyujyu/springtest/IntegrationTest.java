package com.jyujyu.springtest;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

// 부모 클래스같은 경우 테스트가 동작하지 않아도 된다.
@Ignore
// 실제로 테스트 코드가 동작할 때 SpringContext를 Load하지 않지만
// SpringBootTest 어노테이션을 붙여주면 Application API 서버를 실행하는 것처럼 빈들을 모두 스캔하여 등록해주는 과정을 거친다.
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = IntegrationTest.IntegrationTestInitializer.class)
public class IntegrationTest {

	static DockerComposeContainer rdbms;

	static {
		rdbms = new DockerComposeContainer(new File("infra/test/docker-compose.yaml"))
				.withExposedService(
					"local-db",
					3306,
					Wait.forLogMessage(".*ready for connections.*\\s", 1)
						.withStartupTimeout(Duration.ofSeconds(300))
				)
			.withExposedService(
				"local-db-migration",
				0,
				Wait.forLogMessage(".*Migration completed.*\\s", 1)
					.withStartupTimeout(Duration.ofSeconds(300))
			);
		rdbms.start();
	}

	// MySQL 접속 설정 파일을 테스트 코드가 동작할 때 바꿔주는 코드
	static class IntegrationTestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			Map<String, String> properties = new HashMap<>();

			// 정적으로 띄우면 컨테이너 정보를 가져올 수 있음
			var rdbmsHost = rdbms.getServiceHost("local-db", 3306);
			var rdbmsPort = rdbms.getServicePort("local-db", 3306);

			// 동적 주입
			properties.put("spring.datasource.url", "jdbc:mysql://" + rdbmsHost + ":" + rdbmsPort + "/test");

			TestPropertyValues.of(properties)
				.applyTo(applicationContext);
		}
	}
}
