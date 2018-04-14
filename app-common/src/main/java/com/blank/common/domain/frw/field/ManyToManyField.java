package com.blank.common.domain.frw.field;

public class ManyToManyField extends ComplexField {

	private Class<?> acturalType;

	public ManyToManyField(String name, Class<?> type) {
		super(name, type);
	}

	public Class<?> getActuralType() {
		return acturalType;
	}

	public void setActuralType(Class<?> acturalType) {
		this.acturalType = acturalType;
	}

}
