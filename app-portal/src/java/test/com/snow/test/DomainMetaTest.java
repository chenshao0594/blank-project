package com.snow.test;

import org.junit.Test;

import com.blank.common.domain.frw.DomainMeta;
import com.blank.common.domain.frw.DomainMetaBuilder;
import com.blank.domain.User;

public class DomainMetaTest {

	@Test
	public void test() throws ClassNotFoundException {
		DomainMeta meta = DomainMetaBuilder.build(User.class, true);
		System.out.println(meta);

	}

	@Test
	public void integerTest() {
		Integer a = 1000;
		Integer b = 1000;

		System.out.println(a == b);
		Integer a1 = 100;
		Integer b1 = 100;

		System.out.println(a1 == b1);
	}
}
