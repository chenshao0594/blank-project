package com.blank.common.domain.frw.field;

import com.blank.common.domain.frw.DomainMeta;

public class ComplexField extends AbstractField {

	private DomainMeta domainMeta;

	public ComplexField(String name, Class<?> type) {
		super(name, type);
		this.setIsComplex(true);
	}

	public DomainMeta getDomainMeta() {
		return domainMeta;
	}

	public void setDomainMeta(DomainMeta domainMeta) {
		this.domainMeta = domainMeta;
	}

}
