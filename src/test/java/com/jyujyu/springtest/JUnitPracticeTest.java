package com.jyujyu.springtest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 자주 사용하는 것들
 *
 * - assertEquals(): 두 값이 동일한지 확인합니다.
 * - assertNotEquals(): 두 값이 다른지 확인합니다.
 * - assertTrue(): 조건이 참인지 확인합니다.
 * - assertFalse(): 조건이 거짓인지 확인합니다.
 * - assertNull(): 값이 null인지 확인합니다.
 * - assertThrows(): 예외가 발생하는지 확인합니다.
 * - assertNotNull(): 값이 null이 아닌지 확인합니다.
 * - assertIterableEquals(): 두 Iterable 객체가 동일한 요소를 포함하고 있고 순서도 같은지 확인합니다.
 * - assertAll(): 여러 어설션을 그룹화하여 모두 실행하고, 실패한 경우에도 나머지 어설션들을 계속 실행합니다.
 *     Assertions.assertAll("이름",
 *         () -> Assertions.assertEquals("jyujyu", name),
 *         () -> Assertions.assertEquals(25, age)
 *     );
 */
public class JUnitPracticeTest {

	@Test
	public void assertEqualsTest() {
		String expect = "Something";
		String actual = "Something";

		Assertions.assertEquals(expect, actual);
	}

	@Test
	public void assertNotEqualsTest() {
		String expect = "Something";
		String actual = "Hello";

		Assertions.assertNotEquals(expect, actual);
	}

	@Test
	public void assertTrueTest() {
		Integer a = 10;
		Integer b = 10;

		Assertions.assertTrue(a.equals(b));
	}

	@Test
	public void assertFalseTest() {
		Integer a = 10;
		Integer b = 20;

		Assertions.assertFalse(a.equals(b));
	}

	@Test
	public void assertThrowsTest() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			throw new RuntimeException("임의의 에러 발생");
		});
	}

	@Test
	public void assertNotNullTest() {
		String str = "Hello";

		Assertions.assertNotNull(str);
	}

	@Test
	public void assertNullTest() {
		String str = null;

		Assertions.assertNull(str);
	}

	@Test
	public void assertIterableEqualsTest() {
		// 동일한 요소와 순서를 가진 두 Iterator 객체를 비교
		List<Integer> list1 = List.of(1, 2, 3);
		List<Integer> list2 = List.of(1, 2, 3);

		Assertions.assertIterableEquals(list1, list2);
	}

	@Test
	public void	assertAllTest() {
		String expect = "Something";
		String actual = "Something";

		List<Integer> list1 = List.of(1, 2, 3);
		List<Integer> list2 = List.of(1, 2, 3);

		Assertions.assertAll("Assert All", List.of(
				() -> Assertions.assertEquals(expect, actual),
				() -> Assertions.assertIterableEquals(list1, list2)
		));
	}
}
