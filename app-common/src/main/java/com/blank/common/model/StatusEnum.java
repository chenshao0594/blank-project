package com.blank.common.model;

public enum StatusEnum {

	SUCCESS("success"), FAIL("fail");
	private String value;

	StatusEnum(String value) {
		this.value = value;
	}

}
