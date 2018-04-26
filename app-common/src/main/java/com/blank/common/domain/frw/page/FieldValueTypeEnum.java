package com.blank.common.domain.frw.page;

public enum FieldValueTypeEnum {
	STRING("string"), LONG("long"), INTEGER("int"), DOUBLE("double"), ADDRESS("address"), EMAIL("email"), BOOLEAN(
			"boolean"), IMAGE("image");
	private final String value;

	FieldValueTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
