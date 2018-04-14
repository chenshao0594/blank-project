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
}
