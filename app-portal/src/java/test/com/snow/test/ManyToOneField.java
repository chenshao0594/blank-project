package com.snow.test;

public class ManyToOneField extends AbstractField {

	private DomainMeta clazzMeta;

	public ManyToOneField(String name, Class<?> type) {
		super(name, type);
	}

	public DomainMeta getClazzMeta() {
		return clazzMeta;
	}

	public void setClazzMeta(DomainMeta clazzMeta) {
		this.clazzMeta = clazzMeta;
	}

}
