package com.blank.common.domain.frw.page;

import java.util.HashSet;
import java.util.Set;

public class SubPageMeta {
	private final String name;
	private Set<PageFieldMeta> fields = new HashSet<PageFieldMeta>();
	private String actions;

	public SubPageMeta(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Set<PageFieldMeta> getFields() {
		return fields;
	}

	public void setFields(Set<PageFieldMeta> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "SubPageMeta [name=" + name + ", fields=" + fields + ", actions=" + actions + "]";
	}

}
