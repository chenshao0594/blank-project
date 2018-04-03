package com.snow.test;

public class OneToManyField extends AbstractField {

	private Class<?> acturalType;
	private DomainMeta domainMeta;

	public OneToManyField(String name, Class<?> type) {
		super(name, type);
	}

	public Class<?> getActuralType() {
		return acturalType;
	}

	public void setActuralType(Class<?> acturalType) {
		this.acturalType = acturalType;
	}

	public DomainMeta getDomainMeta() {
		return domainMeta;
	}

	public void setDomainMeta(DomainMeta domainMeta) {
		this.domainMeta = domainMeta;
	}

	@Override
	public String toString() {
		return "OneToManyField [acturalType=" + acturalType + ", domainMeta=" + domainMeta + "]";
	}

}
