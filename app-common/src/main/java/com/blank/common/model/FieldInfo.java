package com.blank.common.model;

public class FieldInfo {

	private final String name;
	private final Class<?> clazz;
	private Class<?> actualType;

	public FieldInfo(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
		actualType = clazz;
	}

	public String getName() {
		return name;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Class<?> getActualType() {
		return actualType;
	}

	public void setActualType(Class<?> actualType) {
		this.actualType = actualType;
	}

	@Override
	public String toString() {
		return "FieldInfo [name=" + name + ", clazz=" + clazz + ", actualType=" + actualType + "]";
	}

}
