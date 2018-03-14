package com.snow.test;

import org.junit.Test;

import com.blank.common.domain.frw.DomainMetaBuilder;
import com.blank.domain.User;

public class DomainMetaTest {

	@Test
	public void test() {
		System.out.println(DomainMetaBuilder.build(User.class));

	}
}
