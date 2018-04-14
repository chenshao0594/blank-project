package com.blank.common.domain.frw.field;

public class OneToOneField extends ComplexField {

	private Class<?> acturalType;

	public OneToOneField(String name, Class<?> type) {
		super(name, type);
	}

	public Class<?> getActuralType() {
		return acturalType;
	}

	public void setActuralType(Class<?> acturalType) {
		this.acturalType = acturalType;
	}

}
