package com.jyujyu.springtest.score.application;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.jyujyu.springtest.IntegrationTest;
import com.jyujyu.springtest.NoTransactionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(NoTransactionExtension.class)
public class RedisServiceTest extends IntegrationTest {

  @Autowired private RedisService redisService;

  /**
   * 실제로는 통합 테스트 단계에서 오랜 시간이 소요되기에 JUnit 태깅과 같은 기능으로 테스트를 구분에서 로컬과 CI에서 구분할 수 잇는 테스트를 실행할 수 있도록 한다.
   */
  @Test
  @DisplayName("Redis Get, Set 테스트")
  public void RedisGetSetTest() {
    // given
    String expectValue = "hello";
    String key = "hi";

    // when
    redisService.set(key, expectValue);

    // then
    String actualValue = redisService.get(key);
    assertThat(actualValue).isEqualTo(expectValue);
  }
}
