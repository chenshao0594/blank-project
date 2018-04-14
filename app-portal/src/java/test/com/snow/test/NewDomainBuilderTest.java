package com.snow.test;

import com.blank.common.domain.frw.page.DomainPageBuilder;
import com.blank.domain.User;

public class NewDomainBuilderTest {
	public static void main(String[] args) {

		System.out.println(DomainPageBuilder.build(User.class).getListPage());

	}
}
